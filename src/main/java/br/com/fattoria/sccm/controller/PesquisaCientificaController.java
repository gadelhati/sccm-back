package br.com.fattoria.sccm.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fattoria.sccm.api.AreaConhecimentoModel;
import br.com.fattoria.sccm.api.AreaConhecimentoModelAssembler;
import br.com.fattoria.sccm.api.DocumentosModel;
import br.com.fattoria.sccm.api.DocumentosModelAssembler;
import br.com.fattoria.sccm.api.EquipamentoModel;
import br.com.fattoria.sccm.api.EquipamentoModelAssembler;
import br.com.fattoria.sccm.api.InstituicaoModel;
import br.com.fattoria.sccm.api.InstituicaoModelAssembler;
import br.com.fattoria.sccm.api.PesquisaCientificaApi;
import br.com.fattoria.sccm.api.PesquisaCientificaModel;
import br.com.fattoria.sccm.api.PesquisaCientificaModelAssembler;
import br.com.fattoria.sccm.api.TipoDadoModel;
import br.com.fattoria.sccm.api.TipoDadoModelAssembler;
import br.com.fattoria.sccm.persistence.model.AreaConhecimento;
import br.com.fattoria.sccm.persistence.model.Documento;
import br.com.fattoria.sccm.persistence.model.Equipamento;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaAreaConhecimento;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaAreaConhecimentoPk;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaCoAutor;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaCoAutorPk;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.Sigilo;
import br.com.fattoria.sccm.persistence.model.TipoDado;
import br.com.fattoria.sccm.persistence.repository.AreaConhecimentoRepository;
import br.com.fattoria.sccm.persistence.repository.DocumentosRepository;
import br.com.fattoria.sccm.persistence.repository.EquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.InstituicaoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaAreaConhecimentoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaCoAutorRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaEquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import br.com.fattoria.sccm.persistence.repository.PlataformaRepository;
import br.com.fattoria.sccm.persistence.repository.SigiloRepository;
import br.com.fattoria.sccm.persistence.repository.TipoDadoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "Pesquisa Cientifica")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class PesquisaCientificaController {

	private final Logger log = LoggerFactory.getLogger(PesquisaCientificaController.class);
	private final PesquisaCientificaRepository pesquisaCientificaRepository;
	private final PlataformaRepository plataformaRepository;
	private final SigiloRepository sigiloRepository;
	private final InstituicaoRepository instituicaoRepository;
	private final PesquisaCientificaEquipamentoRepository pesquisaCientificaEquipamentoRepository;
	private final PesquisaCientificaCoAutorRepository pesquisaCientificaCoAutorRepository;
	private final PesquisaCientificaAreaConhecimentoRepository pesquisaCientificaAreaConhecimentoRepository;
	private final AreaConhecimentoRepository areaConhecimentoRepository;
	private final EquipamentoRepository equipamentoRepository;
	private final TipoDadoRepository tipoDadoRepository;
	private final DocumentosRepository documentosRepository;
	
	public PesquisaCientificaController(PesquisaCientificaRepository pesquisaCientificaRepository,
			PlataformaRepository plataformaRepository, SigiloRepository sigiloRepository,
			InstituicaoRepository instituicaoRepository,
			PesquisaCientificaEquipamentoRepository pesquisaCientificaEquipamentoRepository,
			PesquisaCientificaCoAutorRepository pesquisaCientificaCoAutorRepository,
			PesquisaCientificaAreaConhecimentoRepository pesquisaCientificaAreaConhecimentoRepository,
			AreaConhecimentoRepository areaConhecimentoRepository, EquipamentoRepository equipamentoRepository,
			TipoDadoRepository tipoDadoRepository, DocumentosRepository documentosRepository) {
		super();
		this.pesquisaCientificaRepository = pesquisaCientificaRepository;
		this.plataformaRepository = plataformaRepository;
		this.sigiloRepository = sigiloRepository;
		this.instituicaoRepository = instituicaoRepository;
		this.pesquisaCientificaEquipamentoRepository = pesquisaCientificaEquipamentoRepository;
		this.pesquisaCientificaCoAutorRepository = pesquisaCientificaCoAutorRepository;
		this.pesquisaCientificaAreaConhecimentoRepository = pesquisaCientificaAreaConhecimentoRepository;
		this.areaConhecimentoRepository = areaConhecimentoRepository;
		this.equipamentoRepository = equipamentoRepository;
		this.tipoDadoRepository = tipoDadoRepository;
		this.documentosRepository = documentosRepository;
	}

	@PostMapping("/pesquisas_cientificas")
	@ApiOperation(value = "Cria uma Pesquisa Cientifica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pesquisa Cientifica criada"),
    })
	ResponseEntity<PesquisaCientificaModel> create(@Valid @RequestBody PesquisaCientificaApi api) throws URISyntaxException {
		
		log.info("Salvando "+api);
		
		Optional<Plataforma> plataforma = plataformaRepository.findById(api.getIdPlataforma());
		
		Optional<Instituicao> instituicao = instituicaoRepository.findById(api.getIdInstituicao());
		
		Optional<Sigilo> sigilo = sigiloRepository.findById(api.getIdSigilo());
		
        PesquisaCientifica entityPC = api.toEntity();
        entityPC.setDataCadastro(Calendar.getInstance());
        
        entityPC.setPlataforma(plataforma.get());
        entityPC.setInstituicao(instituicao.get());
        entityPC.setSigilo(sigilo.get());
        
        //entity.setComissao(comissaoRepository.save(entity.getComissao()));
        
        entityPC = pesquisaCientificaRepository.save(entityPC);
        
        if(api.getIdsAreasConhecimento() != null && api.getIdsAreasConhecimento().size() > 0) {
        	Iterable<AreaConhecimento> areasConhecimento = areaConhecimentoRepository.findAllById(api.getIdsAreasConhecimento());
            
            for (AreaConhecimento areaConhecimento : areasConhecimento) {
            	PesquisaCientificaAreaConhecimento pesquisaCientificaAreaConhecimento = new PesquisaCientificaAreaConhecimento(new PesquisaCientificaAreaConhecimentoPk(entityPC.getId(), areaConhecimento.getId()));
    			pesquisaCientificaAreaConhecimentoRepository.save(pesquisaCientificaAreaConhecimento);
    		}
        }
        
/*        if(api.getIdsEquipamentos() != null && api.getIdsEquipamentos().size() > 0) {
	        Iterable<Equipamento> equipamentos = equipamentoRepository.findAllById(api.getIdsEquipamentos());
	        
	        for (Equipamento equipamento : equipamentos) {
	        	PesquisaCientificaEquipamento pesquisaCientificaEquipamento = new PesquisaCientificaEquipamento(new PesquisaCientificaEquipamentoPk(entityPC.getId(), equipamento.getId()));
				pesquisaCientificaEquipamentoRepository.save(pesquisaCientificaEquipamento);
			}
        }*/
        
        if(api.getIdsCoParticipantes() != null && api.getIdsCoParticipantes().size() > 0) {
	        Iterable<Instituicao> instituicoes = instituicaoRepository.findAllById(api.getIdsCoParticipantes());
	        
	        for (Instituicao instituicaoCoParticipante : instituicoes) {
	        	PesquisaCientificaCoAutor pesquisaCientificaCoParticipante = new PesquisaCientificaCoAutor(new PesquisaCientificaCoAutorPk(entityPC.getId(), instituicaoCoParticipante.getId()));
	        	pesquisaCientificaCoAutorRepository.save(pesquisaCientificaCoParticipante);
			}
        }
        
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	PesquisaCientificaModel model = assembler.toModel(entityPC);
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entityPC.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/pesquisas_cientificas/{id}")
	@ApiOperation(value = "Atualiza uma Pesquisa Cientifica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pesquisa Cientifica atualizada"),
    })
	ResponseEntity<PesquisaCientificaModel> update(@Valid @RequestBody PesquisaCientificaApi api){
		
		log.info("Alterando "+api);
		
		Optional<Plataforma> plataforma = plataformaRepository.findById(api.getIdPlataforma());
		
		Optional<Instituicao> instituicao = instituicaoRepository.findById(api.getIdInstituicao());
		
		Optional<Sigilo> sigilo = sigiloRepository.findById(api.getIdSigilo());
		
        PesquisaCientifica entityPC = api.toEntity();
        
        entityPC.setPlataforma(plataforma.get());
        entityPC.setInstituicao(instituicao.get());
        entityPC.setSigilo(sigilo.get());
        
        //entity.setComissao(comissaoRepository.save(entity.getComissao()));
        
        entityPC = pesquisaCientificaRepository.save(entityPC);
        
        Collection<PesquisaCientificaAreaConhecimento> areasConhecimentoByPesquisaCientifica =  pesquisaCientificaAreaConhecimentoRepository.findAllByIdPesquisaCientifica(api.getId());
        pesquisaCientificaAreaConhecimentoRepository.deleteAll(areasConhecimentoByPesquisaCientifica);
        
        if(api.getIdsAreasConhecimento() != null && api.getIdsAreasConhecimento().size() > 0) {
        	Iterable<AreaConhecimento> areasConhecimento = areaConhecimentoRepository.findAllById(api.getIdsAreasConhecimento());
            
            for (AreaConhecimento areaConhecimento : areasConhecimento) {
            	PesquisaCientificaAreaConhecimento pesquisaCientificaAreaConhecimento = new PesquisaCientificaAreaConhecimento(new PesquisaCientificaAreaConhecimentoPk(entityPC.getId(), areaConhecimento.getId()));
    			pesquisaCientificaAreaConhecimentoRepository.save(pesquisaCientificaAreaConhecimento);
    		}
        }
        
        /*Collection<PesquisaCientificaEquipamento> equipamentosByPesquisaCientifica = pesquisaCientificaEquipamentoRepository.findAllByIdPesquisaCientifica(api.getId());
        pesquisaCientificaEquipamentoRepository.deleteAll(equipamentosByPesquisaCientifica);
        
        if(api.getIdsEquipamentos() != null && api.getIdsEquipamentos().size() > 0) {
	        Iterable<Equipamento> equipamentos = equipamentoRepository.findAllById(api.getIdsEquipamentos());
	        
	        for (Equipamento equipamento : equipamentos) {
	        	PesquisaCientificaEquipamento pesquisaCientificaEquipamento = new PesquisaCientificaEquipamento(new PesquisaCientificaEquipamentoPk(entityPC.getId(), equipamento.getId()));
				pesquisaCientificaEquipamentoRepository.save(pesquisaCientificaEquipamento);
			}
        }*/
        
        Collection<PesquisaCientificaCoAutor> coAutoresByPesquisaCientifica = pesquisaCientificaCoAutorRepository.findAllByIdPesquisaCientifica(api.getId());
        pesquisaCientificaCoAutorRepository.deleteAll(coAutoresByPesquisaCientifica);
        
        if(api.getIdsCoParticipantes() != null && api.getIdsCoParticipantes().size() > 0) {
	        Iterable<Instituicao> instituicoes = instituicaoRepository.findAllById(api.getIdsCoParticipantes());
	        
	        for (Instituicao instituicaoCoParticipante : instituicoes) {
	        	PesquisaCientificaCoAutor pesquisaCientificaCoParticipante = new PesquisaCientificaCoAutor(new PesquisaCientificaCoAutorPk(entityPC.getId(), instituicaoCoParticipante.getId()));
	        	pesquisaCientificaCoAutorRepository.save(pesquisaCientificaCoParticipante);
			}
        }
        
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	PesquisaCientificaModel model = assembler.toModel(entityPC);
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
	@GetMapping("/pesquisas_cientificas")
    @ApiOperation(value = "Retorna uma lista de Pesquisa Cientifica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pesquisa Cientifica"),
    })
	public ResponseEntity<CollectionModel<PesquisaCientificaModel>> getAll() {
    	
    	Collection<PesquisaCientifica> lista = (Collection<PesquisaCientifica>) pesquisaCientificaRepository.findAll();
    	
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	CollectionModel<PesquisaCientificaModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/pesquisas_cientificas/{id}")
    @ApiOperation(value = "Retorna uma Pesquisa Cientifica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<PesquisaCientificaModel> getById(@PathVariable Long id) {
    	 
    	Optional<PesquisaCientifica> entity = pesquisaCientificaRepository.findById(id);
    	
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	 
    	return entity.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/pesquisas_cientificas/{id}")
    @ApiOperation(value = "Deleta uma Pesquisa Cientifica")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
    	
    	 Optional<PesquisaCientifica> entity = pesquisaCientificaRepository.findById(id);
    	 
    	 return entity.map(
    	            p -> {
    	            	pesquisaCientificaRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Pesquisa Cientifica não encontrado"));
    	
    }
    
	@GetMapping("/pesquisas_cientificas/{id}/areas_conhecimento")
    @ApiOperation(value = "Retorna uma lista de areas de conhecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de areas de conhecimento"),
    })
	public ResponseEntity<CollectionModel<AreaConhecimentoModel>> getAllAreasConhecimentoByIdPesquisaCientifica(@PathVariable Long id) {
    	
    	log.info("listando areas de conhecimento");
    	 
    	Collection<AreaConhecimento> lista = (Collection<AreaConhecimento>) areaConhecimentoRepository.findAllByPesquisaCientificaId(id);
    	
    	AreaConhecimentoModelAssembler assembler = new AreaConhecimentoModelAssembler(); 
    	CollectionModel<AreaConhecimentoModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
	
	@GetMapping("/pesquisas_cientificas/{id}/equipamentos")
    @ApiOperation(value = "Retorna uma lista de equipamentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de equipamentos"),
    })
	public ResponseEntity<CollectionModel<EquipamentoModel>> getAllEquipamentosByIdPesquisaCientifica(@PathVariable Long id) {
    	
    	log.info("listando equipamentos");
    	 
    	Collection<Equipamento> lista = (Collection<Equipamento>) equipamentoRepository.findAllByPesquisaCientificaId(id);
    	
    	EquipamentoModelAssembler assembler = new EquipamentoModelAssembler(); 
    	CollectionModel<EquipamentoModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
	
	@GetMapping("/pesquisas_cientificas/{id}/tipos_dados")
    @ApiOperation(value = "Retorna uma lista de tipos de dados")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de tipos de dados"),
    })
	public ResponseEntity<CollectionModel<TipoDadoModel>> getAllTiposDadosByIdPesquisaCientifica(@PathVariable Long id) {
    	
    	log.info("listando tipos de dados");
    	 
    	Collection<TipoDado> lista = (Collection<TipoDado>) tipoDadoRepository.findAllByPesquisaCientificaId(id);
    	
    	TipoDadoModelAssembler assembler = new TipoDadoModelAssembler(); 
    	CollectionModel<TipoDadoModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
	
	@GetMapping("/pesquisas_cientificas/{id}/coparticipantes")
    @ApiOperation(value = "Retorna uma lista de coparticipantes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de coparticipantes"),
    })
	public ResponseEntity<CollectionModel<InstituicaoModel>> getAllCoparticipantesByIdPesquisaCientifica(@PathVariable Long id) {
    	
    	log.info("listando coparticipantes");
    	 
    	Collection<Instituicao> lista = (Collection<Instituicao>) instituicaoRepository.findAllByPesquisaCientificaId(id);
    	
    	InstituicaoModelAssembler assembler = new InstituicaoModelAssembler(); 
    	CollectionModel<InstituicaoModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
	
	@GetMapping("/pesquisas_cientificas/{id}/documentos")
    @ApiOperation(value = "Retorna uma lista de documentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de documentos"),
    })
	public ResponseEntity<CollectionModel<DocumentosModel>> getAllDocumentosByIdPesquisaCientifica(@PathVariable Long id) {
    	
    	log.info("listando coparticipantes");
    	 
    	Collection<Documento> lista = (Collection<Documento>) documentosRepository.findAllByPesquisaCientificaId(id);
    	
    	DocumentosModelAssembler assembler = new DocumentosModelAssembler(); 
    	CollectionModel<DocumentosModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
	
}
