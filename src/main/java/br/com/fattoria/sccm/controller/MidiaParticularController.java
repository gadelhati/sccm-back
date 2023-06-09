package br.com.fattoria.sccm.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
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

import br.com.fattoria.sccm.api.MidiaParticularApi;
import br.com.fattoria.sccm.api.MidiaParticularModel;
import br.com.fattoria.sccm.api.MidiaParticularModelAssembler;
import br.com.fattoria.sccm.api.MidiaParticularTipoMidiaApi;
import br.com.fattoria.sccm.api.MidiaParticularTipoMidiaModel;
import br.com.fattoria.sccm.api.MidiaParticularTipoMidiaModelAssembler;
import br.com.fattoria.sccm.api.Periodo;
import br.com.fattoria.sccm.api.WrapperListApi;
import br.com.fattoria.sccm.dto.QuantitativoDTO;
import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.model.MidiaParticular;
import br.com.fattoria.sccm.persistence.model.MidiaParticularTipoMidia;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.Situacao;
import br.com.fattoria.sccm.persistence.repository.InstituicaoRepository;
import br.com.fattoria.sccm.persistence.repository.MidiaParticularRepository;
import br.com.fattoria.sccm.persistence.repository.MidiaParticularTipoMidiaRepository;
import br.com.fattoria.sccm.persistence.repository.PlataformaRepository;
import br.com.fattoria.sccm.persistence.repository.RelatorioRepository;
import br.com.fattoria.sccm.persistence.repository.SituacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "Midia Particular")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class MidiaParticularController {
	
	private final Logger log = LoggerFactory.getLogger(MidiaParticularController.class);
	private final MidiaParticularRepository midiaParticularRepository;
	private final MidiaParticularTipoMidiaRepository midiaParticularTipoMidiaRepository;
	private final InstituicaoRepository instituicaoRepository;
	private final PlataformaRepository plataformaRepository;
	private final SituacaoRepository situacaoRepository;
	private final RelatorioRepository relatorioRepository;
		
	public MidiaParticularController(MidiaParticularRepository midiaParticularRepository,
			MidiaParticularTipoMidiaRepository midiaParticularTipoMidiaRepository,
			InstituicaoRepository instituicaoRepository, PlataformaRepository plataformaRepository, 
			SituacaoRepository situacaoRepository, RelatorioRepository relatorioRepository) {
		super();
		this.midiaParticularRepository = midiaParticularRepository;
		this.midiaParticularTipoMidiaRepository = midiaParticularTipoMidiaRepository;
		this.instituicaoRepository = instituicaoRepository;
		this.plataformaRepository = plataformaRepository;
		this.situacaoRepository = situacaoRepository;
		this.relatorioRepository = relatorioRepository;
	}

	@PostMapping("/midias_particulares")
	@ApiOperation(value = "Adiciona uma mídia particular")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Mídia particular criada"),
    })
	ResponseEntity<MidiaParticularModel> create(@Valid @RequestBody MidiaParticularApi midiaParticular) throws URISyntaxException{
		
		log.info("criando midia");
	
        MidiaParticular midiaParticularEntity = midiaParticular.toEntity();
        
        if(midiaParticular.getIdInstituicao() != null) {
        	Optional<Instituicao> instituicao = instituicaoRepository.findById(midiaParticular.getIdInstituicao());
        	midiaParticularEntity.setInstituicao(instituicao.get());
        }
        
        if(midiaParticular.getIdPlataforma() != null) {
        	Optional<Plataforma> plataforma = plataformaRepository.findById(midiaParticular.getIdPlataforma());
        	midiaParticularEntity.setPlataforma(plataforma.get());
        }
        
        if(midiaParticular.getIdSituacao() != null) {
        	Optional<Situacao> situacao = situacaoRepository.findById(midiaParticular.getIdSituacao());
        	midiaParticularEntity.setSituacao(situacao.get());
        }
        
    	MidiaParticularModelAssembler assembler = new MidiaParticularModelAssembler(); 
    	MidiaParticularModel midiaParticularModel = assembler.toModel(midiaParticularRepository.save(midiaParticularEntity));
        
        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(midiaParticularEntity.getId()).toUri();        
        return ResponseEntity.created(uri).body(midiaParticularModel);
	}
	
	@PutMapping("/midias_particulares/{id}")
	@ApiOperation(value = "Atualiza uma mídia particular")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Mídia particular Atualizada"),
    })
	ResponseEntity<MidiaParticularModel> update(@Valid @RequestBody MidiaParticularApi midiaParticular){
		
		log.info("alterando midia");
	
        MidiaParticular midiaParticularEntity = midiaParticular.toEntity();
        
        if(midiaParticular.getIdInstituicao() != null) {
        	Optional<Instituicao> instituicao = instituicaoRepository.findById(midiaParticular.getIdInstituicao());
        	midiaParticularEntity.setInstituicao(instituicao.get());
        }
        
        if(midiaParticular.getIdPlataforma() != null) {
        	Optional<Plataforma> plataforma = plataformaRepository.findById(midiaParticular.getIdPlataforma());
        	midiaParticularEntity.setPlataforma(plataforma.get());
        }
        
        if(midiaParticular.getIdSituacao() != null) {
        	Optional<Situacao> situacao = situacaoRepository.findById(midiaParticular.getIdSituacao());
        	midiaParticularEntity.setSituacao(situacao.get());
        }
        
    	MidiaParticularModelAssembler assembler = new MidiaParticularModelAssembler(); 
    	MidiaParticularModel MidiaParticularModel = assembler.toModel(midiaParticularRepository.save(midiaParticularEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(MidiaParticularModel);

	}
	
	@GetMapping("/midias_particulares/pagina")
    @ApiOperation(value = "Retorna uma lista de midias particulares")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna lista de midias particulares"),
    })
	public ResponseEntity<Page<MidiaParticularModel>> getAll(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(required = false) String search) {
		
		log.info("ObjectUtils.isEmpty(search) "+ObjectUtils.isEmpty(search));
		log.info("paginando midias particulares "+search);
		
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
    	
    	Page<MidiaParticular> lista = null;
    	
    	lista = ObjectUtils.isEmpty(search) ? midiaParticularRepository.findAll(pageRequest) : 
    		midiaParticularRepository.findAllBySearch(pageRequest, "%"+search+"%");
    	
    	MidiaParticularModelAssembler assembler = new MidiaParticularModelAssembler(); 
    	CollectionModel<MidiaParticularModel> listResource = assembler.toCollectionModel(lista.toList());
    	
    	Page<MidiaParticularModel> pageFull = new PageImpl<MidiaParticularModel>(new ArrayList<MidiaParticularModel>(listResource.getContent()), lista.getPageable(), lista.getTotalElements());
    	
	    return ResponseEntity.ok(pageFull);
	}
	
	@GetMapping("/midias_particulares")
    @ApiOperation(value = "Retorna lista de Mídias particulars")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de Mídias particulars"),
    })
	public ResponseEntity<CollectionModel<MidiaParticularModel>> getAll() {
    	
    	log.info("listando midias");
    	 
    	Collection<MidiaParticular> lista = (Collection<MidiaParticular>) midiaParticularRepository.findAll();
    	
    	MidiaParticularModelAssembler assembler = new MidiaParticularModelAssembler(); 
    	CollectionModel<MidiaParticularModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/midias_particulares/{id}")
    @ApiOperation(value = "Retorna uma Mídia particular")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Mídia particular"),
    })
	public ResponseEntity<MidiaParticularModel> getById(@PathVariable Long id) {
    	
    	log.info("Midia por id "+id);
    	 
    	Optional<MidiaParticular> midiaParticular = midiaParticularRepository.findById(id);
    	
    	MidiaParticularModelAssembler assembler = new MidiaParticularModelAssembler(); 
    	 
    	return midiaParticular.map(response -> ResponseEntity.ok().body(assembler.toModel(response))).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/midias_particulares/{id}")
    @ApiOperation(value = "Deleta uma Mídia particular")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("midia por id "+id);
    	
    	 Optional<MidiaParticular> midiaParticular = midiaParticularRepository.findById(id);
    	 
    	 return midiaParticular.map(p -> {
    		 
	    	List<MidiaParticularTipoMidia> allByMidiaParticularId = midiaParticularTipoMidiaRepository.findAllByMidiaParticularId(id);
	    	if(allByMidiaParticularId != null && allByMidiaParticularId.size() > 0) {
	    		midiaParticularTipoMidiaRepository.deleteAll(allByMidiaParticularId);
	    	}
    	    	
    		 midiaParticularRepository.deleteById(id);
    		 return ResponseEntity.noContent().build();
    	 }).orElseThrow(() -> new NotFoundException("Mídia particular não encontrada"));    	
    }
    
	@GetMapping("/midias_particulares/{id}/midias_particulares_tipos_midias")
    @ApiOperation(value = "Retorna uma lista de equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de dados de um equipamento"),
    })
	public ResponseEntity<CollectionModel<MidiaParticularTipoMidiaModel>> getAllMidiaParticularTipoMidiaByIdMidiaParticular(@PathVariable Long id) {
    	
    	log.info("listando midias particulars tipos midias");
    	 
    	Collection<MidiaParticularTipoMidia> lista = (Collection<MidiaParticularTipoMidia>) midiaParticularTipoMidiaRepository.findAllByMidiaParticularId(id);
    	
    	MidiaParticularTipoMidiaModelAssembler assembler = new MidiaParticularTipoMidiaModelAssembler(); 
    	CollectionModel<MidiaParticularTipoMidiaModel> listTipoDadoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listTipoDadoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listTipoDadoResource);
	}
	
	@PutMapping("/midias_particulares/{id}/midias_particulares_tipos_midias")
    @ApiOperation(value = "Salva uma lista de midias particulares tipos midias")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Salva a lista de midias particulares tipos midias"),
    })
	public ResponseEntity<CollectionModel<MidiaParticularTipoMidiaModel>> updateAllMidiaParticularTipoMidiaByIdMidiaParticular(@PathVariable Long id,  @RequestBody WrapperListApi<MidiaParticularTipoMidiaApi> midiasDiversaTipoMidia) {
    	
    	List<MidiaParticularTipoMidia> list = new ArrayList<MidiaParticularTipoMidia>();
    	
    	for (MidiaParticularTipoMidiaApi api : midiasDiversaTipoMidia.getContent()) {
    		list.add(api.toEntity());
		}
    	 
    	List<MidiaParticularTipoMidia> allByMidiaParticularId = midiaParticularTipoMidiaRepository.findAllByMidiaParticularId(id);
    	if(allByMidiaParticularId != null && allByMidiaParticularId.size() > 0) {
    		midiaParticularTipoMidiaRepository.deleteAll(allByMidiaParticularId);
    	}
    	Iterable<MidiaParticularTipoMidia> saveAllList = midiaParticularTipoMidiaRepository.saveAll(list);
    	
    	MidiaParticularTipoMidiaModelAssembler assembler = new MidiaParticularTipoMidiaModelAssembler(); 
    	CollectionModel<MidiaParticularTipoMidiaModel> listResource = assembler.toCollectionModel(saveAllList);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(listResource);
	}
	
	@PostMapping("/midias_particulares/quantidade_cadastradas_por_situacao")
    @ApiOperation(value = "Retorna Quantidade de mídias particulares cadastradas por situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Collection<QuantitativoDTO>> getQuantidadeCadastradasPorSituacao(@RequestBody Periodo periodo) {
    	 
    	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao = relatorioRepository.countMidiasParticularesByDataCadastroBetweenGroupBySituacao(periodo);
    	
    	return ResponseEntity.ok().body(countByDataCadastroBetweenGroupBySituacao);
    }
	
}
