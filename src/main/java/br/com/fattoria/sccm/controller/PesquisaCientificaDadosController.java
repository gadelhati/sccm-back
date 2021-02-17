package br.com.fattoria.sccm.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fattoria.sccm.api.PesquisaCientificaDadosEquipamentosApi;
import br.com.fattoria.sccm.api.PesquisaCientificaDadosModel;
import br.com.fattoria.sccm.api.PesquisaCientificaDadosModelAssembler;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaDados;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaDadosPk;
import br.com.fattoria.sccm.persistence.model.TipoDado;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaDadosRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import br.com.fattoria.sccm.persistence.repository.TipoDadoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Dados de Coleta de Pesquisa Cientifica")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class PesquisaCientificaDadosController {

	private final Logger log = LoggerFactory.getLogger(PesquisaCientificaDadosController.class);
	private final PesquisaCientificaRepository pesquisaCientificaRepository;
	private final PesquisaCientificaDadosRepository pesquisaCientificaDadosRepository;
	private final TipoDadoRepository tipoDadoRepository;
	
	public PesquisaCientificaDadosController(PesquisaCientificaRepository pesquisaCientificaRepository,
			PesquisaCientificaDadosRepository pesquisaCientificaDadosRepository,
			TipoDadoRepository tipoDadoRepository) {
		super();
		this.pesquisaCientificaRepository = pesquisaCientificaRepository;
		this.pesquisaCientificaDadosRepository = pesquisaCientificaDadosRepository;
		this.tipoDadoRepository = tipoDadoRepository;
	}

	@PostMapping("/dados_coleta")
	@ApiOperation(value = "Cria Dados de Coleta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Dados de Coleta adicionados"),
    })
	ResponseEntity<CollectionModel<PesquisaCientificaDadosModel>> create(@Valid @RequestBody PesquisaCientificaDadosEquipamentosApi api) throws URISyntaxException {
		
		log.info("Salvando "+api);
        
		List<PesquisaCientificaDados> listaPCD = new ArrayList<PesquisaCientificaDados>();
        if(api.getIdsDadosEquipamentos() != null && api.getIdsDadosEquipamentos().size() > 0) {
	        Iterable<TipoDado> dadosEquipamentos = tipoDadoRepository.findAllById(api.getIdsDadosEquipamentos());
	        
	        for (TipoDado tipoDado : dadosEquipamentos) {
	        	PesquisaCientificaDados pesquisaCientificaDados = new PesquisaCientificaDados(new PesquisaCientificaDadosPk(api.getIdPesquisaCientifica(), tipoDado.getId()));
	        	listaPCD.add(pesquisaCientificaDadosRepository.save(pesquisaCientificaDados));
			}
        }
        
        PesquisaCientificaDadosModelAssembler assembler = new PesquisaCientificaDadosModelAssembler(); 
    	CollectionModel<PesquisaCientificaDadosModel> model = assembler.toCollectionModel(listaPCD);
 
        
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                    .path("/{id}")
                    .buildAndExpand(api.getIdPesquisaCientifica())
                    .toUri();
        
        return ResponseEntity.created(uri)
                .body(model);
	}
	
	@PutMapping("/dados_coleta/{id}")
	@ApiOperation(value = "Atualiza dados de Coleta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Dados de Coleta atualizados"),
    })
	ResponseEntity<CollectionModel<PesquisaCientificaDadosModel>> update(@Valid @RequestBody PesquisaCientificaDadosEquipamentosApi api){
		
		log.info("Alterando "+api);
		
		List<PesquisaCientificaDados> listaPCD = new ArrayList<PesquisaCientificaDados>();
        if(api.getIdsDadosEquipamentos() != null && api.getIdsDadosEquipamentos().size() > 0) {
	        Iterable<TipoDado> dadosEquipamentos = tipoDadoRepository.findAllById(api.getIdsDadosEquipamentos());
	        
	        for (TipoDado tipoDado : dadosEquipamentos) {
	        	PesquisaCientificaDados pesquisaCientificaDados = new PesquisaCientificaDados(new PesquisaCientificaDadosPk(api.getIdPesquisaCientifica(), tipoDado.getId()));
	        	listaPCD.add(pesquisaCientificaDadosRepository.save(pesquisaCientificaDados));
			}
        }
        
        PesquisaCientificaDadosModelAssembler assembler = new PesquisaCientificaDadosModelAssembler(); 
    	CollectionModel<PesquisaCientificaDadosModel> model = assembler.toCollectionModel(listaPCD);
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}

	
}
