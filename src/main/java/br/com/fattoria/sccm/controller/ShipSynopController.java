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
import br.com.fattoria.sccm.persistence.model.EstacaoMeteorologica;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.model.ShipSynop;
import br.com.fattoria.sccm.persistence.model.Situacao;
import br.com.fattoria.sccm.persistence.repository.ComissaoRepository;
import br.com.fattoria.sccm.persistence.repository.EstacaoMeteorologicaRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import br.com.fattoria.sccm.persistence.repository.PlataformaRepository;
import br.com.fattoria.sccm.persistence.repository.RelatorioRepository;
import br.com.fattoria.sccm.persistence.repository.ShipSynopRepository;
import br.com.fattoria.sccm.persistence.repository.SituacaoRepository;
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
	private SituacaoRepository situacaoRepository;
	private EstacaoMeteorologicaRepository estacaoMeteorologicaRepository;
		
	private final RelatorioRepository relatorioRepository;
	
	private final TypedEntityLinks<ShipSynopModel> links;
		
	public ShipSynopController(ShipSynopRepository shipSynopRepository, ComissaoRepository comissaoRepository,
			PlataformaRepository plataformaRepository, PesquisaCientificaRepository pesquisaCientificaRepository, 
			SituacaoRepository situacaoRepository, EstacaoMeteorologicaRepository estacaoMeteorologicaRepository,
			RelatorioRepository relatorioRepository, EntityLinks entityLinks) {
		this.shipSynopRepository = shipSynopRepository;
		this.comissaoRepository = comissaoRepository;
		this.plataformaRepository = plataformaRepository;
		this.pesquisaCientificaRepository = pesquisaCientificaRepository;
		this.situacaoRepository = situacaoRepository;
		this.estacaoMeteorologicaRepository = estacaoMeteorologicaRepository;
		this.relatorioRepository = relatorioRepository;
		this.links = entityLinks.forType(ShipSynopModel::getId);		
	}
	
	/**     *      * 
     * @param api
     * @return Ship
     * @throws URISyntaxException
     */
	
	@PostMapping("/ship/criar")
	@ApiOperation(value = "Criar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Criado"),
    })
	ResponseEntity<ShipSynopModel> createShip(@Valid @RequestBody ShipSynopApi api) throws URISyntaxException {
        
        Optional<Plataforma> plataforma = plataformaRepository.findById(api.getIdPlataforma());
        
        Optional<Comissao> comissao = comissaoRepository.findById(api.getIdComissao());
        
        Optional<PesquisaCientifica> pc = pesquisaCientificaRepository.findById(api.getIdPesquisaCientifica());
        
        ShipSynop entity = api.toEntity();
    	
    	entity.setPlataforma(plataforma.get());
    	entity.setComissao(comissao.get());
    	entity.setPesquisaCientifica(pc.get());
    	entity.setDados("SHIP");   	
        
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
	
	@PutMapping("/ship/alterar/{id}")
	@ApiOperation(value = "Atualizar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualizado com Sucesso!"),
    })
	ResponseEntity<ShipSynopModel> updateShip(@Valid @RequestBody ShipSynopApi api){
		
		Optional<Situacao> situacao = situacaoRepository.findById(api.getIdSituacao());
		
		Optional<Plataforma> plataforma = plataformaRepository.findById(api.getIdPlataforma());
        
        Optional<Comissao> comissao = comissaoRepository.findById(api.getIdComissao());
        
        Optional<PesquisaCientifica> pc = pesquisaCientificaRepository.findById(api.getIdPesquisaCientifica());
                       		
        ShipSynop entity = api.toEntity();
        
        entity.setSituacao(situacao.get());
        entity.setPlataforma(plataforma.get());
    	entity.setComissao(comissao.get());
    	entity.setPesquisaCientifica(pc.get());
    	entity.setDados("SHIP");
    	        
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	ShipSynopModel model = assembler.toModel(shipSynopRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
	@GetMapping("/ship/todos")
    @ApiOperation(value = "Todos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso"),
    })
	public ResponseEntity<CollectionModel<ShipSynopModel>> getAllShip() {
    	
    	Collection<ShipSynop> lista = (Collection<ShipSynop>) shipSynopRepository.findByDados("SHIP");
    	
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	CollectionModel<ShipSynopModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/ship/buscar/{id}")
    @ApiOperation(value = "Retornar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornar"),
    })
	public ResponseEntity<ShipSynopModel> getByIdShip(@PathVariable Long id) {
    	 
    	Optional<ShipSynop> optional = shipSynopRepository.findById(id);
    	
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	 
    	return optional.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/ship/remover/{id}")
    @ApiOperation(value = "Deletar")
    public ResponseEntity<?> deleteShip(@PathVariable Long id) throws NotFoundException {
    	
    	 Optional<ShipSynop> object = shipSynopRepository.findById(id);
    	 
    	 return object.map(
    	            p -> {
    	            	shipSynopRepository.deleteById(id);
    	              return ResponseEntity.noContent().build();
    	            }).orElseThrow(() -> new NotFoundException("Não encontrado"));
    	
    }
    

    /**     *      * 
     * @param api
     * @return Synop
     * @throws URISyntaxException
     */

    @PostMapping("/synop/criar")
	@ApiOperation(value = "Criar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Criado"),
    })
	ResponseEntity<ShipSynopModel> createSynop(@Valid @RequestBody ShipSynopApi api) throws URISyntaxException {
                
        Optional<Situacao> situacao = situacaoRepository.findById(api.getIdSituacao());
        
        Optional<EstacaoMeteorologica> estacaoMeteorologica = estacaoMeteorologicaRepository.findById(api.getIdEstacaoMeteorologica());
        
        ShipSynop entity = api.toEntity();
    	
    	entity.setSituacao(situacao.get());
    	entity.setEstacaoMeteorologica(estacaoMeteorologica.get());
    	entity.setDados("SYNOP");
        
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
	
	@PutMapping("/synop/alterar/{id}")
	@ApiOperation(value = "Atualizar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualizado com Sucesso!"),
    })
	ResponseEntity<ShipSynopModel> updateSynop(@Valid @RequestBody ShipSynopApi api){
		
        Optional<Situacao> situacao = situacaoRepository.findById(api.getIdSituacao());
        
        Optional<EstacaoMeteorologica> estacaoMeteorologica = estacaoMeteorologicaRepository.findById(api.getIdEstacaoMeteorologica());
                 		
        ShipSynop entity = api.toEntity();
        
    	entity.setSituacao(situacao.get());
    	entity.setEstacaoMeteorologica(estacaoMeteorologica.get());
    	entity.setDados("SYNOP");
        
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	ShipSynopModel model = assembler.toModel(shipSynopRepository.save(entity));
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}
	
	@GetMapping("/synop/todos")
    @ApiOperation(value = "Todos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso"),
    })
	public ResponseEntity<CollectionModel<ShipSynopModel>> getAllSynop() {
    	
    	Collection<ShipSynop> lista = (Collection<ShipSynop>) shipSynopRepository.findByDados("SYNOP");
    	
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	CollectionModel<ShipSynopModel> listResource = assembler.toCollectionModel(lista);
    	
    	final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
    	listResource.add(Link.of(uriString, "self"));
    	
	    return ResponseEntity.ok(listResource);
	}
    
    @GetMapping("/synop/buscar/{id}")
    @ApiOperation(value = "Retornar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornar"),
    })
	public ResponseEntity<ShipSynopModel> getByIdSynop(@PathVariable Long id) {
    	 
    	Optional<ShipSynop> object = shipSynopRepository.findById(id);
    	
    	ShipSynopModelAssembler assembler = new ShipSynopModelAssembler(); 
    	 
    	return object.map(response -> ResponseEntity.ok().body(assembler.toModel(response)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    @DeleteMapping("/synop/remover/{id}")
    @ApiOperation(value = "Deletar")
    public ResponseEntity<?> deleteSynop(@PathVariable Long id) throws NotFoundException {
    	
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
