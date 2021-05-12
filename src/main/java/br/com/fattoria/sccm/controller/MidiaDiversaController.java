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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.fattoria.sccm.api.MidiaDiversaApi;
import br.com.fattoria.sccm.api.MidiaDiversaModel;
import br.com.fattoria.sccm.api.MidiaDiversaModelAssembler;
import br.com.fattoria.sccm.api.MidiaDiversaTipoMidiaApi;
import br.com.fattoria.sccm.api.MidiaDiversaTipoMidiaModel;
import br.com.fattoria.sccm.api.MidiaDiversaTipoMidiaModelAssembler;
import br.com.fattoria.sccm.api.Periodo;
import br.com.fattoria.sccm.api.WrapperListApi;
import br.com.fattoria.sccm.dto.QuantitativoDTO;
import br.com.fattoria.sccm.persistence.model.MidiaDiversa;
import br.com.fattoria.sccm.persistence.model.MidiaDiversaTipoMidia;
import br.com.fattoria.sccm.persistence.model.Situacao;
import br.com.fattoria.sccm.persistence.repository.MidiaDiversaRepository;
import br.com.fattoria.sccm.persistence.repository.MidiaDiversaTipoMidiaRepository;
import br.com.fattoria.sccm.persistence.repository.RelatorioRepository;
import br.com.fattoria.sccm.persistence.repository.SituacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "Midia Diversa")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class MidiaDiversaController {
	
	private final Logger log = LoggerFactory.getLogger(MidiaDiversaController.class);
	private MidiaDiversaRepository midiaDiversaRepository;
	private MidiaDiversaTipoMidiaRepository midiaDiversaTipoMidiaRepository;
	private final SituacaoRepository situacaoRepository;
	private final RelatorioRepository relatorioRepository;
		
	public MidiaDiversaController(MidiaDiversaRepository midiaDiversaRepository,
			MidiaDiversaTipoMidiaRepository midiaDiversaTipoMidiaRepository, 
			SituacaoRepository situacaoRepository, RelatorioRepository relatorioRepository) {
		super();
		this.midiaDiversaRepository = midiaDiversaRepository;
		this.midiaDiversaTipoMidiaRepository = midiaDiversaTipoMidiaRepository;
		this.situacaoRepository = situacaoRepository;
		this.relatorioRepository = relatorioRepository;
	}

	@PostMapping("/midias_diversas")
	@ApiOperation(value = "Adiciona uma mídia diversa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Mídia diversa criada"),
    })
	ResponseEntity<MidiaDiversaModel> create(@Valid @RequestBody MidiaDiversaApi midiaDiversa) throws URISyntaxException{
		
		log.info("criando midia");
	
        MidiaDiversa midiaDiversaEntity = midiaDiversa.toEntity();
        
        if(midiaDiversa.getIdSituacao() != null) {
        	Optional<Situacao> situacao = situacaoRepository.findById(midiaDiversa.getIdSituacao());
        	midiaDiversaEntity.setSituacao(situacao.get());
        }
        
    	MidiaDiversaModelAssembler assembler = new MidiaDiversaModelAssembler(); 
    	MidiaDiversaModel midiaDiversaModel = assembler.toModel(midiaDiversaRepository.save(midiaDiversaEntity));
        
        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(midiaDiversaEntity.getId()).toUri();        
        return ResponseEntity.created(uri).body(midiaDiversaModel);
	}
	
	@PutMapping("/midias_diversas/{id}")
	@ApiOperation(value = "Atualiza uma mídia diversa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Mídia diversa Atualizada"),
    })
	ResponseEntity<MidiaDiversaModel> update(@Valid @RequestBody MidiaDiversaApi midiaDiversa){
		
		log.info("alterando midia");
	
        MidiaDiversa midiaDiversaEntity = midiaDiversa.toEntity();
        
        if(midiaDiversa.getIdSituacao() != null) {
        	Optional<Situacao> situacao = situacaoRepository.findById(midiaDiversa.getIdSituacao());
        	midiaDiversaEntity.setSituacao(situacao.get());
        }
        
    	MidiaDiversaModelAssembler assembler = new MidiaDiversaModelAssembler(); 
    	MidiaDiversaModel MidiaDiversaModel = assembler.toModel(midiaDiversaRepository.save(midiaDiversaEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(MidiaDiversaModel);

	}
	
	@GetMapping("/midias_diversas")
    @ApiOperation(value = "Retorna lista de Mídias diversas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de Mídias diversas"),
    })
	public ResponseEntity<CollectionModel<MidiaDiversaModel>> getAll() {
    	
    	log.info("listando midias");
    	 
    	Collection<MidiaDiversa> lista = (Collection<MidiaDiversa>) midiaDiversaRepository.findAll();
    	
    	MidiaDiversaModelAssembler assembler = new MidiaDiversaModelAssembler(); 
    	CollectionModel<MidiaDiversaModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/midias_diversas/{id}")
    @ApiOperation(value = "Retorna uma Mídia diversa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Mídia diversa"),
    })
	public ResponseEntity<MidiaDiversaModel> getById(@PathVariable Long id) {
    	
    	log.info("Midia por id "+id);
    	 
    	Optional<MidiaDiversa> midiaDiversa = midiaDiversaRepository.findById(id);
    	
    	MidiaDiversaModelAssembler assembler = new MidiaDiversaModelAssembler(); 
    	 
    	return midiaDiversa.map(response -> ResponseEntity.ok().body(assembler.toModel(response))).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/midias_diversas/{id}")
    @ApiOperation(value = "Deleta uma Mídia diversa")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("midia por id "+id);
    	
    	 midiaDiversaRepository.deleteById(id);
    	 Optional<MidiaDiversa> midiaDiversa = midiaDiversaRepository.findById(id);
    	 
    	 return midiaDiversa.map(p -> {midiaDiversaRepository.deleteById(id);
    	 return ResponseEntity.noContent().build();}).orElseThrow(() -> new NotFoundException("Mídia diversa não encontrada"));    	
    }
    
	@GetMapping("/midias_diversas/{id}/midias_diversas_tipos_midias")
    @ApiOperation(value = "Retorna uma lista de equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de dados de um equipamento"),
    })
	public ResponseEntity<CollectionModel<MidiaDiversaTipoMidiaModel>> getAllMidiaDiversaTipoMidiaByIdMidiaDiversa(@PathVariable Long id) {
    	
    	log.info("listando midias diversas tipos midias");
    	 
    	Collection<MidiaDiversaTipoMidia> lista = (Collection<MidiaDiversaTipoMidia>) midiaDiversaTipoMidiaRepository.findAllByMidiaDiversaId(id);
    	
    	MidiaDiversaTipoMidiaModelAssembler assembler = new MidiaDiversaTipoMidiaModelAssembler(); 
    	CollectionModel<MidiaDiversaTipoMidiaModel> listTipoDadoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listTipoDadoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listTipoDadoResource);
	}
	
	@PutMapping("/midias_diversas/{id}/midias_diversas_tipos_midias")
    @ApiOperation(value = "Salva uma lista de midias diversas tipos midias")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Salva a lista de midias diversas tipos midias"),
    })
	public ResponseEntity<CollectionModel<MidiaDiversaTipoMidiaModel>> updateAllMidiaDiversaTipoMidiaByIdMidiaDiversa(@PathVariable Long id,  @RequestBody WrapperListApi<MidiaDiversaTipoMidiaApi> midiasDiversaTipoMidia) {
    	
    	List<MidiaDiversaTipoMidia> list = new ArrayList<MidiaDiversaTipoMidia>();
    	
    	for (MidiaDiversaTipoMidiaApi api : midiasDiversaTipoMidia.getContent()) {
    		list.add(api.toEntity());
		}
    	 
    	List<MidiaDiversaTipoMidia> allByMidiaDiversaId = midiaDiversaTipoMidiaRepository.findAllByMidiaDiversaId(id);
    	if(allByMidiaDiversaId != null && allByMidiaDiversaId.size() > 0) {
    		midiaDiversaTipoMidiaRepository.deleteAll(allByMidiaDiversaId);
    	}
    	Iterable<MidiaDiversaTipoMidia> saveAllList = midiaDiversaTipoMidiaRepository.saveAll(list);
    	
    	MidiaDiversaTipoMidiaModelAssembler assembler = new MidiaDiversaTipoMidiaModelAssembler(); 
    	CollectionModel<MidiaDiversaTipoMidiaModel> listResource = assembler.toCollectionModel(saveAllList);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(listResource);
	}
	
	@PostMapping("/midias_diversas/quantidade_cadastradas_por_situacao")
    @ApiOperation(value = "Retorna Quantidade de mídias particulares cadastradas por situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Collection<QuantitativoDTO>> getQuantidadeCadastradasPorSituacao(@RequestBody Periodo periodo) {
    	
    	log.info("periodo => ", periodo);
    	log.info("inicio => ", periodo.getDataInicio());
    	log.info("fim => ", periodo.getDataFim());
    	log.info("teste => ", periodo.getTeste());
    	 
    	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao = relatorioRepository.countMidiasDiversasByDataCadastroBetweenGroupBySituacao(periodo);
    	
    	return ResponseEntity.ok().body(countByDataCadastroBetweenGroupBySituacao);
    }
	
}
