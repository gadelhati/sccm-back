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

import br.com.fattoria.sccm.persistence.model.TipoPlataforma;
import br.com.fattoria.sccm.persistence.repository.TipoPlataformaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(value = "Tipos de Plataformas")
@RequestMapping(value = "/tipos_plataformas")
public class TiposPlataformaController implements CRUDApi<TipoPlataforma, Long> {

    private static final Logger log = LoggerFactory.getLogger(TiposPlataformaController.class);

    private final TipoPlataformaRepository tipoPlataformaRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public TiposPlataformaController(TipoPlataformaRepository tipoPlataformaRepository) {
        this.tipoPlataformaRepository = tipoPlataformaRepository;
    }

//    @RolesAllowed("user")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma lista de tipos de plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de tipos de plataformas"),
    })
	public List<TipoPlataforma> getAll() {
    	 
    	 List<TipoPlataforma> lista = (List<TipoPlataforma>) tipoPlataformaRepository.findAll();
    	 
         return lista;
	}
    
//    @RolesAllowed("user")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna um tipo de plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna um tipo de plataforma"),
    })
	public Optional<TipoPlataforma> getById(@PathVariable Long id) {
    	 
    	Optional<TipoPlataforma> optional = tipoPlataformaRepository.findById(id);
    	 
         return optional;
	}
    
//    @RolesAllowed("user")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Salva um tipo de plataforma")
    public TipoPlataforma save(@Valid @RequestBody TipoPlataforma tipoPlataforma){
    	return tipoPlataformaRepository.save(tipoPlataforma);
    }
    
//    @RolesAllowed("user")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta um tipo de plataforma")
    public void delete(@PathVariable Long id){
    	tipoPlataformaRepository.deleteById(id);
    }

}
