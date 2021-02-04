package br.com.fattoria.sccm.controller;

import java.net.URI;
import java.net.URISyntaxException;
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
import br.com.fattoria.sccm.api.EquipamentoApi;
import br.com.fattoria.sccm.api.EquipamentoModel;
import br.com.fattoria.sccm.api.EquipamentoModelAssembler;
import br.com.fattoria.sccm.api.TipoDadoModel;
import br.com.fattoria.sccm.api.TipoDadoModelAssembler;
import br.com.fattoria.sccm.persistence.model.AreaConhecimento;
import br.com.fattoria.sccm.persistence.model.AreaConhecimentoEquipamento;
import br.com.fattoria.sccm.persistence.model.AreaConhecimentoEquipamentoPK;
import br.com.fattoria.sccm.persistence.model.AreaTecnica;
import br.com.fattoria.sccm.persistence.model.Equipamento;
import br.com.fattoria.sccm.persistence.model.EquipamentoDados;
import br.com.fattoria.sccm.persistence.model.EquipamentoDadosPK;
import br.com.fattoria.sccm.persistence.model.MetodoAmostragem;
import br.com.fattoria.sccm.persistence.model.TipoDado;
import br.com.fattoria.sccm.persistence.repository.AreaConhecimentoEquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.AreaConhecimentoRepository;
import br.com.fattoria.sccm.persistence.repository.AreaTecnicaRepository;
import br.com.fattoria.sccm.persistence.repository.EquipamentoDadosRepository;
import br.com.fattoria.sccm.persistence.repository.EquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.MetodoAmostragemRepository;
import br.com.fattoria.sccm.persistence.repository.TipoDadoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "equipamento")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class EquipamentoController {
	
	private final Logger log = LoggerFactory.getLogger(EquipamentoController.class);
	private EquipamentoRepository equipamentoRepository;
	private AreaConhecimentoEquipamentoRepository areaConhecimentoEquipamentoRepository;
	private EquipamentoDadosRepository equipamentoDadosRepository;
	private AreaTecnicaRepository areaTecnicaRepository;
	private MetodoAmostragemRepository metodoAmostragemRepository;
	private TipoDadoRepository tipoDadoRepository;
	private AreaConhecimentoRepository areaConhecimentoRepository;
	
	public EquipamentoController(EquipamentoRepository equipamentoRepository, AreaConhecimentoEquipamentoRepository areaConhecimentoEquipamentoRepository, 
			EquipamentoDadosRepository equipamentoDadosRepository, AreaTecnicaRepository areaTecnicaRepository, MetodoAmostragemRepository metodoAmostragemRepository, 
			TipoDadoRepository tipoDadoRepository, AreaConhecimentoRepository areaConhecimentoRepository) {
		this.equipamentoRepository = equipamentoRepository;
		this.areaConhecimentoEquipamentoRepository = areaConhecimentoEquipamentoRepository;
		this.areaTecnicaRepository = areaTecnicaRepository;
		this.metodoAmostragemRepository = metodoAmostragemRepository;
		this.equipamentoDadosRepository = equipamentoDadosRepository;
		this.tipoDadoRepository = tipoDadoRepository;
		this.areaConhecimentoRepository = areaConhecimentoRepository;
	}
	
	@PostMapping("/equipamentos")
	@ApiOperation(value = "Add um Equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Equipamento incluido com sucesso"),
    })
	ResponseEntity<EquipamentoModel> create(@Valid @RequestBody EquipamentoApi equipamento) throws URISyntaxException{
		
		log.info("criando quipamento");
	
        Equipamento equipamentoEntity = equipamento.toEntity();
        
    	if(equipamento.getIdAreaTecnica() != null) {
    		Optional<AreaTecnica> areaTecnica = areaTecnicaRepository.findById(equipamento.getIdAreaTecnica());
    		equipamentoEntity.setAreaTecnica(areaTecnica.get());
    	}
    	
    	if(equipamento.getIdMetodoAmostragem() != null) {
    		Optional<MetodoAmostragem> metodoAmostragem = metodoAmostragemRepository.findById(equipamento.getIdMetodoAmostragem());
    		equipamentoEntity.setMetodoAmostragem(metodoAmostragem.get());
    	}
    	
    	equipamentoEntity = equipamentoRepository.save(equipamentoEntity);
    	
    	if(equipamento.getIdsAreaConhecimento() != null && equipamento.getIdsAreaConhecimento().size() > 0) {
    		for (Long idAreaConhecimento : equipamento.getIdsAreaConhecimento()) {
    			AreaConhecimentoEquipamento areaConhecimentoEquipamento = new AreaConhecimentoEquipamento(new AreaConhecimentoEquipamentoPK(idAreaConhecimento, equipamentoEntity.getId()));
    			areaConhecimentoEquipamentoRepository.save(areaConhecimentoEquipamento);
			}
    	}
    	
    	if(equipamento.getIdsTipoDado() != null && equipamento.getIdsTipoDado().size() > 0) {
    		for (Long idTipoDado : equipamento.getIdsTipoDado()) {
    			EquipamentoDados equipamentoDados = new EquipamentoDados(new EquipamentoDadosPK(equipamentoEntity.getId(), idTipoDado));
    			equipamentoDadosRepository.save(equipamentoDados);
			}
    	}
        
    	EquipamentoModelAssembler assembler = new EquipamentoModelAssembler(); 
    	EquipamentoModel paisModel = assembler.toModel(equipamentoEntity);
        
        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(equipamentoEntity.getId()).toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/equipamentos/{id}")
	@ApiOperation(value = "Atualiza um Equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Equipamento atualizado"),
    })
	ResponseEntity<EquipamentoModel> update(@Valid @RequestBody EquipamentoApi equipamento){
		
		log.info("alterando equipamento");
	
		Equipamento equipamentoEntity = equipamento.toEntity();
        
    	if(equipamento.getIdAreaTecnica() != null) {
    		Optional<AreaTecnica> areaTecnica = areaTecnicaRepository.findById(equipamento.getIdAreaTecnica());
    		equipamentoEntity.setAreaTecnica(areaTecnica.get());
    	}
    	
    	if(equipamento.getIdMetodoAmostragem() != null) {
    		Optional<MetodoAmostragem> metodoAmostragem = metodoAmostragemRepository.findById(equipamento.getIdMetodoAmostragem());
    		equipamentoEntity.setMetodoAmostragem(metodoAmostragem.get());
    	}
    	
    	equipamentoEntity = equipamentoRepository.save(equipamentoEntity);
    	
    	List<AreaConhecimentoEquipamento> areasConhecimento = areaConhecimentoEquipamentoRepository.findAllByEquipamentoId(equipamentoEntity.getId());
    	
    	if(areasConhecimento != null && areasConhecimento.size() > 0) {
    		areaConhecimentoEquipamentoRepository.deleteAll(areasConhecimento);
    	}
    	
    	if(equipamento.getIdsAreaConhecimento() != null && equipamento.getIdsAreaConhecimento().size() > 0) {
    		for (Long idAreaConhecimento : equipamento.getIdsAreaConhecimento()) {
    			AreaConhecimentoEquipamento areaConhecimentoEquipamento = new AreaConhecimentoEquipamento(new AreaConhecimentoEquipamentoPK(idAreaConhecimento, equipamentoEntity.getId()));
    			areaConhecimentoEquipamentoRepository.save(areaConhecimentoEquipamento);
			}
    	}
    	
    	List<EquipamentoDados> dadosEquipamentos = equipamentoDadosRepository.findAllByEquipamentoId(equipamentoEntity.getId());
        
    	if(dadosEquipamentos != null && dadosEquipamentos.size() > 0) {
    		equipamentoDadosRepository.deleteAll(dadosEquipamentos);
    	}
    	
    	if(equipamento.getIdsTipoDado() != null && equipamento.getIdsTipoDado().size() > 0) {
    		for (Long idTipoDado : equipamento.getIdsTipoDado()) {
    			EquipamentoDados equipamentoDados = new EquipamentoDados(new EquipamentoDadosPK(equipamentoEntity.getId(), idTipoDado));
    			equipamentoDadosRepository.save(equipamentoDados);
			}
    	}
    	
    	EquipamentoModelAssembler assembler = new EquipamentoModelAssembler(); 
    	EquipamentoModel equipamentoModel = assembler.toModel(equipamentoRepository.save(equipamentoEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(equipamentoModel);

	}
	
	@GetMapping("/equipamentos")
    @ApiOperation(value = "Retorna uma lista de equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de equipamento"),
    })
	public ResponseEntity<CollectionModel<EquipamentoModel>> getAll() {
    	
    	log.info("listando equipamento");
    	 
    	Collection<Equipamento> lista = (Collection<Equipamento>) equipamentoRepository.findAll();
    	
    	EquipamentoModelAssembler assembler = new EquipamentoModelAssembler(); 
    	CollectionModel<EquipamentoModel> listEquipamentoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listEquipamentoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listEquipamentoResource);
	}
    
    @GetMapping("/equipamentos/{id}")
    @ApiOperation(value = "Retorna um equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma equipamento"),
    })
	public ResponseEntity<EquipamentoModel> getById(@PathVariable Long id) {
    	
    	log.info("Equipamento por id "+id);
    	 
    	Optional<Equipamento> pais = equipamentoRepository.findById(id);
    	
    	EquipamentoModelAssembler assembler = new EquipamentoModelAssembler(); 
    	 
    	return pais.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/equipamentos/{id}")
    @ApiOperation(value = "Deleta um equipamento")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("Equipamento por id "+id);
    	
    	 equipamentoRepository.deleteById(id);
    	 Optional<Equipamento> pais = equipamentoRepository.findById(id);
    	 
    	 return pais.map(p -> {equipamentoRepository.deleteById(id); 
    	 return ResponseEntity.noContent().build();}).orElseThrow(() -> new NotFoundException("Equipamento não encontrada"));    	
    }
    
	@PutMapping("/equipamentos/active/{id}")
	@ApiOperation(value = "Ativa/Desativa um equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Equipamento atualizada"),
    })
	ResponseEntity<EquipamentoModel> active(@Valid @PathVariable Long id){
		
		log.info("alterando Equipamento");
		
		Optional<Equipamento> equipamento = equipamentoRepository.findById(id);
		
		EquipamentoModelAssembler assembler = new EquipamentoModelAssembler(); 
		
    	return equipamento.map(response -> {
    		response.setAtivo(!response.isAtivo());
    		equipamentoRepository.save(response);
    		return ResponseEntity.ok().body(assembler.toModel(response));
    	}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	
	@GetMapping("/equipamentos/{id}/tipo_dados")
    @ApiOperation(value = "Retorna uma lista de equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de dados de um equipamento"),
    })
	public ResponseEntity<CollectionModel<TipoDadoModel>> getAllTipoDadosByIdEquipamento(@PathVariable Long id) {
    	
    	log.info("listando equipamento");
    	 
    	Collection<TipoDado> lista = (Collection<TipoDado>) tipoDadoRepository.findAllByEquipamentoId(id);
    	
    	TipoDadoModelAssembler assembler = new TipoDadoModelAssembler(); 
    	CollectionModel<TipoDadoModel> listTipoDadoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listTipoDadoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listTipoDadoResource);
	}
	
	@GetMapping("/equipamentos/{id}/areas_conhecimento")
    @ApiOperation(value = "Retorna uma lista de equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de dados de um equipamento"),
    })
	public ResponseEntity<CollectionModel<AreaConhecimentoModel>> getAllAreaConhecimentoByIdEquipamento(@PathVariable Long id) {
    	
    	log.info("listando equipamento");
    	 
    	Collection<AreaConhecimento> lista = (Collection<AreaConhecimento>) areaConhecimentoRepository.findAllByEquipamentoId(id);
    	
    	AreaConhecimentoModelAssembler assembler = new AreaConhecimentoModelAssembler(); 
    	CollectionModel<AreaConhecimentoModel> listAreaConhcimentoResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listAreaConhcimentoResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listAreaConhcimentoResource);
	}

}
