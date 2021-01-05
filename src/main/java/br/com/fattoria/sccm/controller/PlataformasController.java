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

import br.com.fattoria.sccm.persistence.model.Plataforma;
import br.com.fattoria.sccm.persistence.repository.PlataformaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(value = "Plataformas")
@RequestMapping(value = "/plataformas")
public class PlataformasController implements CRUDApi<Plataforma, Long> {

    private static final Logger log = LoggerFactory.getLogger(PlataformasController.class);

    private final PlataformaRepository plataformaRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public PlataformasController(PlataformaRepository PlataformaRepository) {
        this.plataformaRepository = PlataformaRepository;
    }

//    @RolesAllowed("user")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma lista de plataformas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de plataformas"),
    })
	public List<Plataforma> getAll() {
    	 
    	 List<Plataforma> lista = (List<Plataforma>) plataformaRepository.findAll();
    	 
         return lista;
	}
    
//    @RolesAllowed("user")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma plataforma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma plataforma"),
    })
	public Optional<Plataforma> getById(@PathVariable Long id) {
    	 
    	Optional<Plataforma> optional = plataformaRepository.findById(id);
    	 
         return optional;
	}
    
//    @RolesAllowed("user")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Salva uma plataforma")
    public Plataforma save(@Valid @RequestBody Plataforma Plataforma){
    	return plataformaRepository.save(Plataforma);
    }
    
//    @RolesAllowed("user")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta uma plataforma")
    public void delete(@PathVariable Long id){
    	plataformaRepository.deleteById(id);
    }

}
