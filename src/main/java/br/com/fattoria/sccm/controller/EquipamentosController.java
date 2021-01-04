package br.com.fattoria.sccm.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.persistence.model.Equipamento;
import br.com.fattoria.sccm.persistence.repository.EquipamentoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(value = "Equipamentos")
public class EquipamentosController implements CRUDApi<Equipamento, Long> {

    private static final Logger log = LoggerFactory.getLogger(EquipamentosController.class);

    private final EquipamentoRepository equipamentoRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public EquipamentosController(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    @RolesAllowed("user")
    @GetMapping(value = "/equipamentos", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma lista de equipamentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de equipamentos"),
    })
	public List<Equipamento> getAll() {
    	 
    	 List<Equipamento> lista = (List<Equipamento>) equipamentoRepository.findAll();
    	 
         return lista;
	}
    
    @RolesAllowed("user")
    @GetMapping(value = "/equipamentos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma lista de equipamentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de equipamentos"),
    })
	public Optional<Equipamento> getById(@PathVariable Long id) {
    	 
    	Optional<Equipamento> equipamento = equipamentoRepository.findById(id);
    	 
         return equipamento;
	}
    
    @PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Salva um equipamento")
    public Equipamento save(@Valid @RequestBody Equipamento equipamento){
    	return equipamentoRepository.save(equipamento);
    }
    
    @DeleteMapping(value = "/deletar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deletar um equipamento")
    public void delete(@PathVariable Long id){
    	equipamentoRepository.deleteById(id);
    }

}
