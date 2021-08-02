package br.com.fattoria.sccm.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fattoria.sccm.api.AreaConhecimentoModel;
import br.com.fattoria.sccm.api.AreaConhecimentoModelAssembler;
import br.com.fattoria.sccm.api.ControleInternoModel;
import br.com.fattoria.sccm.api.ControleInternoModelAssembler;
import br.com.fattoria.sccm.api.EquipamentoModel;
import br.com.fattoria.sccm.api.EquipamentoModelAssembler;
import br.com.fattoria.sccm.api.InstituicaoModel;
import br.com.fattoria.sccm.api.InstituicaoModelAssembler;
import br.com.fattoria.sccm.api.Periodo;
import br.com.fattoria.sccm.api.PesquisaCientificaApi;
import br.com.fattoria.sccm.api.PesquisaCientificaModel;
import br.com.fattoria.sccm.api.PesquisaCientificaModelAssembler;
import br.com.fattoria.sccm.api.SearchApi;
import br.com.fattoria.sccm.api.TipoDadoModel;
import br.com.fattoria.sccm.api.TipoDadoModelAssembler;
import br.com.fattoria.sccm.dto.QuantitativoDTO;
import br.com.fattoria.sccm.persistence.model.AreaConhecimento;
import br.com.fattoria.sccm.persistence.model.AssinaturaPC;
import br.com.fattoria.sccm.persistence.model.ControleInterno;
import br.com.fattoria.sccm.persistence.model.Documento;
import br.com.fattoria.sccm.persistence.model.Equipamento;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaAreaConhecimento;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaAreaConhecimentoPk;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaCoAutor;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaCoAutorPk;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaEquipamento;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaEquipamentoPk;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.Sigilo;
import br.com.fattoria.sccm.persistence.model.Situacao;
import br.com.fattoria.sccm.persistence.model.TipoDado;
import br.com.fattoria.sccm.persistence.model.XML;
import br.com.fattoria.sccm.persistence.repository.AreaConhecimentoRepository;
import br.com.fattoria.sccm.persistence.repository.AssinaturaPCRepository;
import br.com.fattoria.sccm.persistence.repository.ControleInternoRepository;
import br.com.fattoria.sccm.persistence.repository.DocumentosRepository;
import br.com.fattoria.sccm.persistence.repository.EquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.InstituicaoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaAreaConhecimentoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaCoAutorRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaEquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import br.com.fattoria.sccm.persistence.repository.PlataformaRepository;
import br.com.fattoria.sccm.persistence.repository.RelatorioRepository;
import br.com.fattoria.sccm.persistence.repository.SigiloRepository;
import br.com.fattoria.sccm.persistence.repository.SituacaoRepository;
import br.com.fattoria.sccm.persistence.repository.TipoDadoRepository;
import br.com.fattoria.sccm.persistence.repository.XMLRepository;
import br.com.fattoria.sccm.reports.ReportFichaPesquisaCientifica;
import br.com.fattoria.sccm.reports.data.AreaConhecimentoDTO;
import br.com.fattoria.sccm.reports.data.DocumentosDTO;
import br.com.fattoria.sccm.reports.data.EquipamentoDTO;
import br.com.fattoria.sccm.reports.data.FichaPesquisaCientificaDTO;
import br.com.fattoria.sccm.reports.data.TipoDadoDTO;
import br.com.fattoria.sccm.reports.templates.busines.SequenceGenerator;
import br.com.fattoria.sccm.reports.templates.busines.SequenceModel;
import br.com.fattoria.sccm.service.DocumentStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import net.sf.jasperreports.engine.JRException;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "Pesquisa Cientifica")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class PesquisaCientificaController {

	private final Logger log = LoggerFactory.getLogger(PesquisaCientificaController.class);
	private final PesquisaCientificaRepository pesquisaCientificaRepository;
	private final PlataformaRepository plataformaRepository;
	private final SigiloRepository sigiloRepository;
	private final SituacaoRepository situacaoRepository;
	private final InstituicaoRepository instituicaoRepository;
	private final PesquisaCientificaCoAutorRepository pesquisaCientificaCoAutorRepository;
	private final PesquisaCientificaAreaConhecimentoRepository pesquisaCientificaAreaConhecimentoRepository;
	private final PesquisaCientificaEquipamentoRepository pesquisaCientificaEquipamentoRepository;
	private final AreaConhecimentoRepository areaConhecimentoRepository;
	private final EquipamentoRepository equipamentoRepository;
	private final TipoDadoRepository tipoDadoRepository;
	private final DocumentosRepository documentosRepository;
	private final ControleInternoRepository controleInternoRepository;
	private final SequenceGenerator sequenceGenerator;
	private final XMLRepository xmlRepository;
	private final AssinaturaPCRepository assinaturaPCRepository;
	private final RelatorioRepository relatorioRepository;
	
	@Autowired
	private DocumentStorageService documentStorageService;
	
	public PesquisaCientificaController(PesquisaCientificaRepository pesquisaCientificaRepository,
			PlataformaRepository plataformaRepository, SigiloRepository sigiloRepository,
			SituacaoRepository situacaoRepository,
			InstituicaoRepository instituicaoRepository,
			PesquisaCientificaCoAutorRepository pesquisaCientificaCoAutorRepository,
			PesquisaCientificaAreaConhecimentoRepository pesquisaCientificaAreaConhecimentoRepository,
			PesquisaCientificaEquipamentoRepository pesquisaCientificaEquipamentoRepository,
			AreaConhecimentoRepository areaConhecimentoRepository, EquipamentoRepository equipamentoRepository,
			TipoDadoRepository tipoDadoRepository, DocumentosRepository documentosRepository,
			ControleInternoRepository controleInternoRepository, SequenceGenerator sequenceGenerator,
			XMLRepository xmlRepository, DocumentStorageService documentStorageService, AssinaturaPCRepository assinaturaPCRepository,
			RelatorioRepository relatorioRepository) {
		super();
		this.pesquisaCientificaRepository = pesquisaCientificaRepository;
		this.plataformaRepository = plataformaRepository;
		this.sigiloRepository = sigiloRepository;
		this.situacaoRepository = situacaoRepository;
		this.instituicaoRepository = instituicaoRepository;
		this.pesquisaCientificaCoAutorRepository = pesquisaCientificaCoAutorRepository;
		this.pesquisaCientificaAreaConhecimentoRepository = pesquisaCientificaAreaConhecimentoRepository;
		this.pesquisaCientificaEquipamentoRepository = pesquisaCientificaEquipamentoRepository;
		this.areaConhecimentoRepository = areaConhecimentoRepository;
		this.equipamentoRepository = equipamentoRepository;
		this.tipoDadoRepository = tipoDadoRepository;
		this.documentosRepository = documentosRepository;
		this.controleInternoRepository = controleInternoRepository;
		this.sequenceGenerator = sequenceGenerator;
		this.xmlRepository = xmlRepository;
		this.documentStorageService = documentStorageService;
		this.assinaturaPCRepository = assinaturaPCRepository;
		this.relatorioRepository = relatorioRepository;
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
		
		Optional<Situacao> situacao = situacaoRepository.findById(api.getIdSituacao());
		
        PesquisaCientifica entityPC = api.toEntity();
        entityPC.setDataCadastro(Calendar.getInstance());
        
        entityPC.setPlataforma(plataforma.get());
        entityPC.setInstituicao(instituicao.get());
        entityPC.setSigilo(sigilo.get());
        entityPC.setSituacao(situacao.get());
        
		String sequence = sequenceGenerator.getSequence(SequenceModel.build("NUMERO_PC"));
		log.info("Numero PC gerado "+sequence);
		entityPC.setNumeroPC(sequence);
        
        entityPC = pesquisaCientificaRepository.save(entityPC);
        
        if(api.getIdsAreasConhecimento() != null && api.getIdsAreasConhecimento().size() > 0) {
        	Iterable<AreaConhecimento> areasConhecimento = areaConhecimentoRepository.findAllById(api.getIdsAreasConhecimento());
            
            for (AreaConhecimento areaConhecimento : areasConhecimento) {
            	PesquisaCientificaAreaConhecimento pesquisaCientificaAreaConhecimento = new PesquisaCientificaAreaConhecimento(new PesquisaCientificaAreaConhecimentoPk(entityPC.getId(), areaConhecimento.getId()));
    			pesquisaCientificaAreaConhecimentoRepository.save(pesquisaCientificaAreaConhecimento);
    		}
        }
        
        if(api.getIdsEquipamentos() != null && api.getIdsEquipamentos().size() > 0) {
	        Iterable<Equipamento> equipamentos = equipamentoRepository.findAllById(api.getIdsEquipamentos());
	        
	        for (Equipamento equipamento : equipamentos) {
	        	PesquisaCientificaEquipamento pesquisaCientificaEquipamento = new PesquisaCientificaEquipamento(new PesquisaCientificaEquipamentoPk(entityPC.getId(), equipamento.getId()));
				pesquisaCientificaEquipamentoRepository.save(pesquisaCientificaEquipamento);
			}
        }
        
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
	ResponseEntity<PesquisaCientificaModel> update(HttpServletRequest request, @Valid @RequestBody PesquisaCientificaApi api){
		
		log.info("Alterando "+api);

		//UsuarioApi usuarioApi = UsuarioApi.toUsuarioApi(accessToken.get);
		
		Optional<Plataforma> plataforma = plataformaRepository.findById(api.getIdPlataforma());
		
		Optional<Instituicao> instituicao = instituicaoRepository.findById(api.getIdInstituicao());
		
		Optional<Sigilo> sigilo = sigiloRepository.findById(api.getIdSigilo());
		
		Optional<Situacao> situacao = situacaoRepository.findById(api.getIdSituacao());
		
        PesquisaCientifica entityPC = api.toEntity();
        
        entityPC.setPlataforma(plataforma.get());
        entityPC.setInstituicao(instituicao.get());
        entityPC.setSigilo(sigilo.get());
        entityPC.setSituacao(situacao.get());
        
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
        
        Collection<PesquisaCientificaEquipamento> equipamentosByPesquisaCientifica = pesquisaCientificaEquipamentoRepository.findAllByIdPesquisaCientifica(api.getId());
        pesquisaCientificaEquipamentoRepository.deleteAll(equipamentosByPesquisaCientifica);
        
        if(api.getIdsEquipamentos() != null && api.getIdsEquipamentos().size() > 0) {
	        Iterable<Equipamento> equipamentos = equipamentoRepository.findAllById(api.getIdsEquipamentos());
	        
	        for (Equipamento equipamento : equipamentos) {
	        	PesquisaCientificaEquipamento pesquisaCientificaEquipamento = new PesquisaCientificaEquipamento(new PesquisaCientificaEquipamentoPk(entityPC.getId(), equipamento.getId()));
				pesquisaCientificaEquipamentoRepository.save(pesquisaCientificaEquipamento);
			}
        }
        
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
    	
    	Collection<PesquisaCientifica> lista = (Collection<PesquisaCientifica>) pesquisaCientificaRepository.findAll(Sort.by("numeroPC").descending());
    	
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	CollectionModel<PesquisaCientificaModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
	
	@GetMapping("/pesquisas_cientificas/pagina")
    @ApiOperation(value = "Retorna uma lista de Pesquisa Cientifica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pesquisa Cientifica"),
    })
	public ResponseEntity<Page<PesquisaCientificaModel>> getAll(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(required = false) String search) {
		
		log.info("paginando pesquisas_cientificas "+search);
		
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("numeroPC").descending());
    	
    	Page<PesquisaCientifica> lista = null;
    	
    	log.info("ObjectUtils.isEmpty(search) "+ObjectUtils.isEmpty(search));
    	
    	lista = ObjectUtils.isEmpty(search) ? pesquisaCientificaRepository.findAll(pageRequest) : 
    		pesquisaCientificaRepository.findAllBySearch(pageRequest, "%"+search+"%");
    	
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	CollectionModel<PesquisaCientificaModel> listResource = assembler.toCollectionModel(lista.toList());
    	
//    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
//    	listResource.add(Link.of(uriString, "self"));
    	
    	Page<PesquisaCientificaModel> pageFull = new PageImpl<PesquisaCientificaModel>(new ArrayList<PesquisaCientificaModel>(listResource.getContent()), lista.getPageable(), lista.getTotalElements());
    	
	    return ResponseEntity.ok(pageFull);
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
	
	@GetMapping("/pesquisas_cientificas/{id}/controleInterno")
    @ApiOperation(value = "Retorna uma lista controles internos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de controles internos"),
    })
	public ResponseEntity<CollectionModel<ControleInternoModel>> getAllControlesInternosByIdPesquisaCientifica(@PathVariable Long id) {
    	
    	log.info("listando controles internos");
    	 
    	Collection<ControleInterno> lista = (Collection<ControleInterno>) controleInternoRepository.findAllByPesquisaCientificaId(id);
    	
    	ControleInternoModelAssembler assembler = new ControleInternoModelAssembler(); 
    	CollectionModel<ControleInternoModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
	
	@GetMapping("/pesquisas_cientificas/sequence")
	public ResponseEntity<?> getSequence() { 
    	
		String sequence = sequenceGenerator.getSequence(SequenceModel.build("NUMERO_PC"));
		log.info("Numero PC gerado "+sequence);
    	
	    return ResponseEntity.ok(sequence);
	}
	
	@GetMapping({"/pesquisas_cientificas/xml", "/pesquisas_cientificas/{id}/xml"})
	public ResponseEntity<?> getXml(@PathVariable Long id) throws IOException {
		
		Optional<XML> entityXML = xmlRepository.findById(1L);
		
		Optional<PesquisaCientifica> entityPC = pesquisaCientificaRepository.findById(id);
		
		XML xml = entityXML.get();
		
//		String path = "C:\\Users\\Victor\\";
//		
//		File file = new File(path + "teste.xml");
//				
//		FileWriter archive = new FileWriter(file);
//		PrintWriter writer = new PrintWriter(archive);
//			
//		writer.println(xml.getXml().toString());			
//		archive.close();
//			
//		Resource resource = null;
//		try {
//			resource = documentStorageService.loadFileAsResource(file.getName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		String contentType = null;
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(byteArrayOutputStream);
		
		if(entityXML.isPresent() && entityPC.isPresent()) {
			
			PesquisaCientifica pesquisaCientifica = entityPC.get();
			
			Collection<AreaConhecimento> listaAreaConhecimento = (Collection<AreaConhecimento>) areaConhecimentoRepository.findAllByPesquisaCientificaId(id);
			Collection<Equipamento> listaEquipamentos = (Collection<Equipamento>) equipamentoRepository.findAllByPesquisaCientificaId(id);
			Collection<TipoDado> listaTiposDados = (Collection<TipoDado>) tipoDadoRepository.findAllByPesquisaCientificaId(id);
			
			pesquisaCientifica.setListaAreaConhecimento((List<AreaConhecimento>) listaAreaConhecimento);
			pesquisaCientifica.setListaEquipamentos((List<Equipamento>) listaEquipamentos);
			pesquisaCientifica.setListaTiposDados((List<TipoDado>) listaTiposDados);
			
			
			writer.println(xml.getFormat(pesquisaCientifica));
		}
		
		writer.close();
		byteArrayOutputStream.flush();
		byteArrayOutputStream.close();
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + xml.getId() + ".xml\"")								
				.body(byteArrayOutputStream.toByteArray());
	    
	}
	
	@GetMapping({"/pesquisas_cientificas/pdf", "/pesquisas_cientificas/{id}/pdf"})
	public ResponseEntity<?> getPDF(@PathVariable Long id) throws IOException, JRException {
		
		FichaPesquisaCientificaDTO dto = pesquisaCientificaRepository.getIdFichaPesquisaCientificaView(id);
		
		AreaConhecimentoDTO areaConhecimentoDTO = new AreaConhecimentoDTO();		
		dto.setListaAreaConhecimento(areaConhecimentoDTO.getListToListDTO(areaConhecimentoRepository.findAllByPesquisaCientificaId(id)));
		
		TipoDadoDTO tipoDadoDTO = new TipoDadoDTO();		
		dto.setListaVariaveis(tipoDadoDTO.getListToListDTO(tipoDadoRepository.findAllByPesquisaCientificaId(id)));
		
		EquipamentoDTO equipamentoDTO = new EquipamentoDTO();		
		dto.setListaEquipamentos(equipamentoDTO.getListToListDTO(equipamentoRepository.findAllByPesquisaCientificaId(id)));
		
		DocumentosDTO documentosDTO = new DocumentosDTO();		
		dto.setListaDocumentos(documentosDTO.getListToListDTO((List<Documento>)documentosRepository.findAllByPesquisaCientificaId(id)));
		
		List<AssinaturaPC> assinaturas = assinaturaPCRepository.findByAtivo(Boolean.TRUE);
		
		int cont = 1;
		
		for (AssinaturaPC assinatura : assinaturas) {
			
			if (cont == 1) {
				dto.setNome_assinatura1(assinatura.getNome());
				dto.setPatente1(assinatura.getPatente());
				dto.setCargo1(assinatura.getCargo());
				dto.setDestino1(assinatura.getDestino().getDestino());
			} else if (cont == 2) {
				dto.setNome_assinatura2(assinatura.getNome());
				dto.setPatente2(assinatura.getPatente());
				dto.setCargo2(assinatura.getCargo());
				dto.setDestino2(assinatura.getDestino().getDestino());
			} else if (cont == 3) {
				dto.setNome_assinatura3(assinatura.getNome());
				dto.setPatente3(assinatura.getPatente());
				dto.setCargo3(assinatura.getCargo());
				dto.setDestino3(assinatura.getDestino().getDestino());
			}
			
			cont++;
		}
		
		ReportFichaPesquisaCientifica report = new ReportFichaPesquisaCientifica(dto);
		
		report.addParametro("IMG_LOGO", ReportFichaPesquisaCientifica.class.
				getResourceAsStream("/br/com/fattoria/sccm/reports/img/logo-chm.png"));
		
		report.addJasperSubReport("SUB_AREA_CONHECIMENTO", ReportFichaPesquisaCientifica.class.
				getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subReport_fichaPesquisaCientifica_areaConhecimento.jasper"));
		
		report.addJasperSubReport("SUB_VARIAVEIS_COLETADAS", ReportFichaPesquisaCientifica.class.
				getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subReport_fichaPesquisaCientifica_dados.jasper"));
		
		report.addJasperSubReport("SUB_EQUIPAMENTOS", ReportFichaPesquisaCientifica.class.
				getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subReport_fichaPesquisaCientifica_equipamento.jasper"));
		
		report.addJasperSubReport("SUB_DOCUMENTOS", ReportFichaPesquisaCientifica.class.
				getResourceAsStream("/br/com/fattoria/sccm/reports/templates/subReport_fichaPesquisaCientifica_documentos.jasper"));
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		report.geraPDF(byteArrayOutputStream);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/pdf"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dto.getFitoteca() + ".pdf\"")								
				.body(byteArrayOutputStream.toByteArray());
	    
	}
	
    @PostMapping("/pesquisas_cientificas/quantidade_cadastradas")
    @ApiOperation(value = "Retorna Quantidade de Pesquisas Cientificas Cadastradas por periodo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Long> getQuantidadeCadastradas(@RequestBody Periodo periodo) {
    	 
    	Long quantidade = relatorioRepository.countByDataCadastroBetween(periodo);
    	
    	return ResponseEntity.ok().body(quantidade);
    }
    
    @PostMapping("/pesquisas_cientificas/quantidade_cadastradas_por_tipo_comissao")
    @ApiOperation(value = "Retorna Quantidade de Pesquisas Cientificas Cadastradas por tipo de comissão e periodo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Collection<QuantitativoDTO>> getQuantidadeCadastradasPorTipoComissao(@RequestBody Periodo periodo) {
    	 
    	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupByNomeComissao = relatorioRepository.countByDataCadastroBetweenGroupByTipoComissao(periodo);
    	
    	return ResponseEntity.ok().body(countByDataCadastroBetweenGroupByNomeComissao);
    }
    
    @PostMapping("/pesquisas_cientificas/quantidade_cadastradas_por_situacao")
    @ApiOperation(value = "Retorna Quantidade de Pesquisas Cientificas Cadastradas por situação e periodo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Collection<QuantitativoDTO>> getQuantidadeCadastradasPorSituacao(@RequestBody Periodo periodo) {
    	
    	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao = relatorioRepository.countByDataCadastroBetweenGroupBySituacao(periodo);
    	
    	return ResponseEntity.ok().body(countByDataCadastroBetweenGroupBySituacao);
    }
    
    @PostMapping("/pesquisas_cientificas/quantidade_cadastradas_por_equipamentos")
    @ApiOperation(value = "Retorna Quantidade de Pesquisas Cientificas Cadastradas por equipamentos e periodo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Collection<QuantitativoDTO>> getQuantidadeCadastradasPorEquipamentos(@RequestBody Periodo periodo) {
    	 
    	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao = relatorioRepository.countByDataCadastroBetweenGroupByEquipamentos(periodo);
    	
    	return ResponseEntity.ok().body(countByDataCadastroBetweenGroupBySituacao);
    }
    
    @PostMapping("/pesquisas_cientificas/pendentes_por_periodo")
    @ApiOperation(value = "Retorna Quantidade de Pesquisas Cientificas Cadastradas por equipamentos e periodo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<CollectionModel<PesquisaCientificaModel>> findByDataCadastroIdSituacaoBetween(@RequestBody Periodo periodo) {
    	 
    	Collection<PesquisaCientifica> pesquisaCientificas = relatorioRepository.findByDataCadastroIdSituacaoBetween(periodo, 4L);
    	
    	PesquisaCientificaModelAssembler pesquisaCientificaModelAssembler = new PesquisaCientificaModelAssembler();
    	
    	return ResponseEntity.ok().body(pesquisaCientificaModelAssembler.toCollectionModel(pesquisaCientificas));
    }
    
    @PostMapping("/pesquisas_cientificas/searchNumeroPC")
    @ApiOperation(value = "Retorna uma lista de Pesquisa Cientifica")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Pesquisa Cientifica"),
    })
	public ResponseEntity<CollectionModel<PesquisaCientificaModel>> getAllByNumeroPC(@RequestBody SearchApi searchApi) {
    	
    	Collection<PesquisaCientifica> lista = (Collection<PesquisaCientifica>) pesquisaCientificaRepository.findByNumeroPCContainingIgnoreCase(
    			searchApi.getSearch() != null ? searchApi.getSearch() : "");
    	
    	PesquisaCientificaModelAssembler assembler = new PesquisaCientificaModelAssembler(); 
    	CollectionModel<PesquisaCientificaModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
	
}
