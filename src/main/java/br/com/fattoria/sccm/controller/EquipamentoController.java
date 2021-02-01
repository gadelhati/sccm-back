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

import br.com.fattoria.sccm.api.EquipamentoApi;
import br.com.fattoria.sccm.api.EquipamentoModel;
import br.com.fattoria.sccm.api.EquipamentoModelAssembler;
import br.com.fattoria.sccm.persistence.model.AreaConhecimentoEquipamento;
import br.com.fattoria.sccm.persistence.model.AreaConhecimentoEquipamentoPK;
import br.com.fattoria.sccm.persistence.model.AreaTecnica;
import br.com.fattoria.sccm.persistence.model.Equipamento;
import br.com.fattoria.sccm.persistence.model.MetodoAmostragem;
import br.com.fattoria.sccm.persistence.model.UnidadeMedida;
import br.com.fattoria.sccm.persistence.repository.AreaConhecimentoEquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.AreaTecnicaRepository;
import br.com.fattoria.sccm.persistence.repository.EquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.MetodoAmostragemRepository;
import br.com.fattoria.sccm.persistence.repository.UnidadeMedidaRepository;
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
	private AreaTecnicaRepository areaTecnicaRepository;
	private MetodoAmostragemRepository metodoAmostragemRepository;
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	public EquipamentoController(EquipamentoRepository equipamentoRepository, AreaConhecimentoEquipamentoRepository areaConhecimentoEquipamentoRepository, 
			AreaTecnicaRepository areaTecnicaRepository, MetodoAmostragemRepository metodoAmostragemRepository, UnidadeMedidaRepository unidadeMedidaRepository) {
		this.equipamentoRepository = equipamentoRepository;
		this.areaConhecimentoEquipamentoRepository = areaConhecimentoEquipamentoRepository;
		this.areaTecnicaRepository = areaTecnicaRepository;
		this.metodoAmostragemRepository = metodoAmostragemRepository;
		this.unidadeMedidaRepository = unidadeMedidaRepository;
	}
	
	@PostMapping("/equipamento")
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
    	
    	if(equipamento.getIdUnidadeMedida() != null) {
    		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(equipamento.getIdUnidadeMedida());
    		equipamentoEntity.setUnidadeMedida(unidadeMedida.get());
    	}
    	
    	equipamentoEntity = equipamentoRepository.save(equipamentoEntity);
    	
    	if(equipamento.getIdsAreaConhecimento() != null && equipamento.getIdsAreaConhecimento().size() > 0) {
    		for (Long idAreaConhecimento : equipamento.getIdsAreaConhecimento()) {
    			AreaConhecimentoEquipamento areaConhecimentoEquipamento = new AreaConhecimentoEquipamento(new AreaConhecimentoEquipamentoPK(idAreaConhecimento, equipamentoEntity.getId()));
    			areaConhecimentoEquipamentoRepository.save(areaConhecimentoEquipamento);
			}
    	}
        
    	EquipamentoModelAssembler assembler = new EquipamentoModelAssembler(); 
    	EquipamentoModel paisModel = assembler.toModel(equipamentoEntity);
        
        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(equipamentoEntity.getId()).toUri();
        
        return ResponseEntity.created(uri)
                .body(paisModel);
	}
	
	@PutMapping("/equipamento/{id}")
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
    	
    	if(equipamento.getIdUnidadeMedida() != null) {
    		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(equipamento.getIdUnidadeMedida());
    		equipamentoEntity.setUnidadeMedida(unidadeMedida.get());
    	}
    	
    	equipamentoEntity = equipamentoRepository.save(equipamentoEntity);
    	
    	List<AreaConhecimentoEquipamento> findAllByIdEquipamento = areaConhecimentoEquipamentoRepository.findAllByIdEquipamento(equipamentoEntity.getId());
    	
    	if(findAllByIdEquipamento != null && findAllByIdEquipamento.size() > 0) {
    		areaConhecimentoEquipamentoRepository.deleteAll(findAllByIdEquipamento);
    	}
    	
    	if(equipamento.getIdsAreaConhecimento() != null && equipamento.getIdsAreaConhecimento().size() > 0) {
    		for (Long idAreaConhecimento : equipamento.getIdsAreaConhecimento()) {
    			AreaConhecimentoEquipamento areaConhecimentoEquipamento = new AreaConhecimentoEquipamento(new AreaConhecimentoEquipamentoPK(idAreaConhecimento, equipamentoEntity.getId()));
    			areaConhecimentoEquipamentoRepository.save(areaConhecimentoEquipamento);
			}
    	}
        
    	EquipamentoModelAssembler assembler = new EquipamentoModelAssembler(); 
    	EquipamentoModel equipamentoModel = assembler.toModel(equipamentoRepository.save(equipamentoEntity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(equipamentoModel);

	}
	
	@GetMapping("/equipamento")
    @ApiOperation(value = "Retorna uma lista de equipamento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de equipamento"),
    })
	public ResponseEntity<CollectionModel<EquipamentoModel>> getAll() {
    	
    	log.info("listando equipamento");
    	 
    	Collection<Equipamento> lista = (Collection<Equipamento>) equipamentoRepository.findAll();
    	
    	EquipamentoModelAssembler assembler = new EquipamentoModelAssembler(); 
    	CollectionModel<EquipamentoModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/equipamento/{id}")
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
    
    @DeleteMapping("/equipamento/{id}")
    @ApiOperation(value = "Deleta um equipamento")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
    	
    	 log.info("Equipamento por id "+id);
    	
    	 equipamentoRepository.deleteById(id);
    	 Optional<Equipamento> pais = equipamentoRepository.findById(id);
    	 
    	 return pais.map(p -> {equipamentoRepository.deleteById(id); 
    	 return ResponseEntity.noContent().build();}).orElseThrow(() -> new NotFoundException("Equipamento não encontrada"));    	
    }

}
