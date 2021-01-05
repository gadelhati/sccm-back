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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.persistence.model.TipoMidia;
import br.com.fattoria.sccm.persistence.repository.TipoMidiaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(value = "Tipos de Midias")
@RequestMapping(value = "/tipos_midias")
public class TiposMidiaController implements CRUDApi<TipoMidia, Long> {

    private static final Logger log = LoggerFactory.getLogger(TiposMidiaController.class);

    private final TipoMidiaRepository tipoMidiaRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public TiposMidiaController(TipoMidiaRepository tipoMidiaRepository) {
        this.tipoMidiaRepository = tipoMidiaRepository;
    }

//    @RolesAllowed("user")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma lista de instituições")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de tipos de midias"),
    })
	public List<TipoMidia> getAll() {
    	 
    	 List<TipoMidia> lista = (List<TipoMidia>) tipoMidiaRepository.findAll();
    	 
         return lista;
	}
    
    @RolesAllowed("user")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna um tipo de plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna um tipo de midia"),
    })
	public Optional<TipoMidia> getById(@PathVariable Long id) {
    	 
    	Optional<TipoMidia> optional = tipoMidiaRepository.findById(id);
    	 
         return optional;
	}
    
//    @RolesAllowed("user")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Salva um tipo de midia")
    public TipoMidia save(@Valid @RequestBody TipoMidia tipoMidia){
    	return tipoMidiaRepository.save(tipoMidia);
    }
    
//    @RolesAllowed("user")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta um tipo de midia")
    public void delete(@PathVariable Long id){
    	tipoMidiaRepository.deleteById(id);
    }

}
