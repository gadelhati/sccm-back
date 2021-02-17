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

import br.com.fattoria.sccm.api.PesquisaCientificaEquipamentosApi;
import br.com.fattoria.sccm.api.PesquisaCientificaEquipamentosModel;
import br.com.fattoria.sccm.api.PesquisaCientificaEquipamentosModelAssembler;
import br.com.fattoria.sccm.persistence.model.Equipamento;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaEquipamento;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaEquipamentoPk;
import br.com.fattoria.sccm.persistence.repository.EquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaEquipamentoRepository;
import br.com.fattoria.sccm.persistence.repository.PesquisaCientificaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Dados de Coleta de Pesquisa Cientifica")
@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class PesquisaCientificaEquipamentosController {

	private final Logger log = LoggerFactory.getLogger(PesquisaCientificaEquipamentosController.class);
	private final PesquisaCientificaRepository pesquisaCientificaRepository;
	private final PesquisaCientificaEquipamentoRepository pesquisaCientificaEquipamentoRepository;
	private final EquipamentoRepository equipamentoRepository;
	
	public PesquisaCientificaEquipamentosController(PesquisaCientificaRepository pesquisaCientificaRepository,
			PesquisaCientificaEquipamentoRepository pesquisaCientificaEquipamentoRepository,
			EquipamentoRepository equipamentoRepository) {
		super();
		this.pesquisaCientificaRepository = pesquisaCientificaRepository;
		this.pesquisaCientificaEquipamentoRepository = pesquisaCientificaEquipamentoRepository;
		this.equipamentoRepository = equipamentoRepository;
	}

	@PostMapping("/dados_coleta")
	@ApiOperation(value = "Cria Dados de Coleta")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Dados de Coleta adicionados"),
    })
	ResponseEntity<CollectionModel<PesquisaCientificaEquipamentosModel>> create(@Valid @RequestBody PesquisaCientificaEquipamentosApi api) throws URISyntaxException {
		
		log.info("Salvando "+api);
		
		List<PesquisaCientificaEquipamento> listaPCE = new ArrayList<PesquisaCientificaEquipamento>();
        if(api.getIdsEquipamentos() != null && api.getIdsEquipamentos().size() > 0) {
	        Iterable<Equipamento> equipamentos = equipamentoRepository.findAllById(api.getIdsEquipamentos());
	        
	        for (Equipamento equipamento : equipamentos) {
	        	PesquisaCientificaEquipamento pesquisaCientificaEquipamento = new PesquisaCientificaEquipamento(new PesquisaCientificaEquipamentoPk(api.getIdPesquisaCientifica(), equipamento.getId()));
	        	listaPCE.add(pesquisaCientificaEquipamentoRepository.save(pesquisaCientificaEquipamento));
			}
        }
        
        PesquisaCientificaEquipamentosModelAssembler assembler = new PesquisaCientificaEquipamentosModelAssembler(); 
    	CollectionModel<PesquisaCientificaEquipamentosModel> model = assembler.toCollectionModel(listaPCE);
 
        
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
	ResponseEntity<CollectionModel<PesquisaCientificaEquipamentosModel>> update(@Valid @RequestBody PesquisaCientificaEquipamentosApi api){
		
		log.info("Alterando "+api);
		
		List<PesquisaCientificaEquipamento> listaPCE = new ArrayList<PesquisaCientificaEquipamento>();
        if(api.getIdsEquipamentos() != null && api.getIdsEquipamentos().size() > 0) {
	        Iterable<Equipamento> equipamentos = equipamentoRepository.findAllById(api.getIdsEquipamentos());
	        
	        for (Equipamento equipamento : equipamentos) {
	        	PesquisaCientificaEquipamento pesquisaCientificaEquipamento = new PesquisaCientificaEquipamento(new PesquisaCientificaEquipamentoPk(api.getIdPesquisaCientifica(), equipamento.getId()));
	        	listaPCE.add(pesquisaCientificaEquipamentoRepository.save(pesquisaCientificaEquipamento));
			}
        }
        
        PesquisaCientificaEquipamentosModelAssembler assembler = new PesquisaCientificaEquipamentosModelAssembler(); 
    	CollectionModel<PesquisaCientificaEquipamentosModel> model = assembler.toCollectionModel(listaPCE);
        
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(model);

	}

	
}
