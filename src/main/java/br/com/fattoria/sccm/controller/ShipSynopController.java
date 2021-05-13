package br.com.fattoria.sccm.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.TypedEntityLinks;
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

import br.com.fattoria.sccm.api.Periodo;
import br.com.fattoria.sccm.api.ShipSynopApi;
import br.com.fattoria.sccm.api.ShipSynopModel;
import br.com.fattoria.sccm.api.ShipSynopModelAssembler;
import br.com.fattoria.sccm.dto.QuantitativoDTO;
import br.com.fattoria.sccm.persistence.model.Comissao;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.ShipSynop;
import br.com.fattoria.sccm.persistence.repository.ComissaoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import br.com.fattoria.sccm.persistence.repository.PlataformaRepository;
import br.com.fattoria.sccm.persistence.repository.RelatorioRepository;
import br.com.fattoria.sccm.persistence.repository.ShipSynopRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@Api(value = "shipSynop")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class ShipSynopController {

	private final Logger log = LoggerFactory.getLogger(ShipSynopController.class);
	private ShipSynopRepository shipSynopRepository;
	private ComissaoRepository comissaoRepository;
	private PlataformaRepository plataformaRepository;
	private PesquisaCientificaRepository pesquisaCientificaRepository;
	private final RelatorioRepository relatorioRepository;
	
	private final TypedEntityLinks<ShipSynopModel> links;
	
	public ShipSynopController(ShipSynopRepository shipSynopRepository, 
			ComissaoRepository comissaoRepository,
			PlataformaRepository plataformaRepository, 
			PesquisaCientificaRepository pesquisaCientificaRepository, 
			RelatorioRepository relatorioRepository, EntityLinks entityLinks) {
		this.shipSynopRepository = shipSynopRepository;
		this.comissaoRepository = comissaoRepository;
		this.plataformaRepository = plataformaRepository;
		this.pesquisaCientificaRepository = pesquisaCientificaRepository;
		this.relatorioRepository = relatorioRepository;
		this.links = entityLinks.forType(ShipSynopModel::getId);
	}
	
	@PostMapping("/shipSynop")
	@ApiOperation(value = "Cria um Destino")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Destino criado"),
    })
	ResponseEntity<ShipSynopModel> create(@Valid @RequestBody ShipSynopApi api) throws URISyntaxException {
        
        Optional<Plataforma> plataforma = plataformaRepository.findById(api.getIdPlataforma());
        
        Optional<Comissao> comissao = comissaoRepository.findById(api.getIdComissao());
        
        Optional<PesquisaCientifica> pc = pesquisaCientificaRepository.findById(api.getIdPesquisaCientifica());
        
        ShipSynop entity = api.toEntity();
    	
    	entity.setPlataforma(plataforma.get());
    	entity.setComissao(comissao.get());
    	entity.setPesquisaCientifica(pc.get());
        
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	ShipSynopModel model = assembler.toModel(shipSynopRepository.save(entity));
    	 
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/shipSynop/{id}")
	@ApiOperation(value = "Atualizar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualizado com Sucesso!"),
    })
	ResponseEntity<ShipSynopModel> update(@Valid @RequestBody ShipSynopApi api){
		
		Optional<Plataforma> plataforma = plataformaRepository.findById(api.getIdPlataforma());
        
        Optional<Comissao> comissao = comissaoRepository.findById(api.getIdComissao());
        
        Optional<PesquisaCientifica> pc = pesquisaCientificaRepository.findById(api.getIdPesquisaCientifica());
        		
        ShipSynop entity = api.toEntity();
        
        entity.setPlataforma(plataforma.get());
    	entity.setComissao(comissao.get());
    	entity.setPesquisaCientifica(pc.get());
        
        
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	ShipSynopModel paisModel = assembler.toModel(shipSynopRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(paisModel);

	}
	
	@GetMapping("/shipSynop")
    @ApiOperation(value = "Todos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso"),
    })
	public ResponseEntity<CollectionModel<ShipSynopModel>> getAll() {
    	
    	Collection<ShipSynop> lista = (Collection<ShipSynop>) shipSynopRepository.findAll();
    	
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	CollectionModel<ShipSynopModel> listPlataformaResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listPlataformaResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listPlataformaResource);
	}
    
    @GetMapping("/shipSynop/{id}")
    @ApiOperation(value = "Retorna um objeto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornou um objeto"),
    })
	public ResponseEntity<ShipSynopModel> getById(@PathVariable Long id) {
    	 
    	Optional<ShipSynop> destino = shipSynopRepository.findById(id);
    	
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	 
    	return destino.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/shipSynop/{id}")
    @ApiOperation(value = "Deleta")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
    	
    	 Optional<ShipSynop> object = shipSynopRepository.findById(id);
    	 
    	 return object.map(
    	            p -> {
    	            	shipSynopRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Não encontrado"));
    	
    }
    
	@PostMapping("/shipSynop/modelos_ship_por_situacao")
    @ApiOperation(value = "Retorna Quantidade de modelos ship por situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Collection<QuantitativoDTO>> getModelosShipPorSituacao(@RequestBody Periodo periodo) {
    	
    	log.info("periodo => ", periodo);
    	log.info("inicio => ", periodo.getDataInicio());
    	log.info("fim => ", periodo.getDataFim());
    	log.info("teste => ", periodo.getTeste());
    	 
    	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao = relatorioRepository.sumModelosObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "ship");
    	
    	return ResponseEntity.ok().body(countByDataCadastroBetweenGroupBySituacao);
    }
	
	@PostMapping("/shipSynop/modelos_synop_por_situacao")
    @ApiOperation(value = "Retorna Quantidade de modelos synop por situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Collection<QuantitativoDTO>> getModelosSynopPorSituacao(@RequestBody Periodo periodo) {
    	
    	log.info("periodo => ", periodo);
    	log.info("inicio => ", periodo.getDataInicio());
    	log.info("fim => ", periodo.getDataFim());
    	log.info("teste => ", periodo.getTeste());
    	 
    	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao = relatorioRepository.sumModelosObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "synop");
    	
    	return ResponseEntity.ok().body(countByDataCadastroBetweenGroupBySituacao);
    }
	
	@PostMapping("/shipSynop/informacoes_ship_por_situacao")
    @ApiOperation(value = "Retorna Quantidade de informações ship por situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Collection<QuantitativoDTO>> getInformacoesShipPorSituacao(@RequestBody Periodo periodo) {
    	
    	log.info("periodo => ", periodo);
    	log.info("inicio => ", periodo.getDataInicio());
    	log.info("fim => ", periodo.getDataFim());
    	log.info("teste => ", periodo.getTeste());
    	 
    	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao = relatorioRepository.sumInformacaoObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "ship");
    	
    	return ResponseEntity.ok().body(countByDataCadastroBetweenGroupBySituacao);
    }
	
	@PostMapping("/shipSynop/informacoes_synop_por_situacao")
    @ApiOperation(value = "Retorna Quantidade de informações synop por situacao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma Pesquisa Cientifica"),
    })
	public ResponseEntity<Collection<QuantitativoDTO>> getInformacoesSynopPorSituacao(@RequestBody Periodo periodo) {
    	
    	log.info("periodo => ", periodo);
    	log.info("inicio => ", periodo.getDataInicio());
    	log.info("fim => ", periodo.getDataFim());
    	log.info("teste => ", periodo.getTeste());
    	 
    	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao = relatorioRepository.sumInformacaoObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(periodo, "synop");
    	
    	return ResponseEntity.ok().body(countByDataCadastroBetweenGroupBySituacao);
    }
	
}
