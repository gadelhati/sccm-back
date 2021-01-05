package br.com.fattoria.sccm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.persistence.model.Empresa;
import br.com.fattoria.sccm.persistence.repository.EmpresaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(value = "Empresas")
@RequestMapping(value = "/empresas")
public class EmpresasController implements CRUDApi<Empresa, Long> {

    private static final Logger log = LoggerFactory.getLogger(EmpresasController.class);

    private final EmpresaRepository empresaRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public EmpresasController(EmpresaRepository EmpresaRepository) {
        this.empresaRepository = EmpresaRepository;
    }

//    @RolesAllowed("user")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma lista de tipos de empresa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de empresas"),
    })
	public List<Empresa> getAll() {
    	 
    	 List<Empresa> lista = (List<Empresa>) empresaRepository.findAll();
    	 
         return lista;
	}
    
//    @RolesAllowed("user")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Salva um tipo de empresa")
    public Empresa save(@Valid @RequestBody Empresa empresa){
    	log.info(empresa.toString());
    	return empresaRepository.save(empresa);
    }
    
//    @RolesAllowed("user")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta um tipo empresa")
    public void delete(@PathVariable Long id){
    	empresaRepository.deleteById(id);
    }
    
    
//  @RolesAllowed("user")
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Retorna um tipo de empresa")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Retorna uma empresa"),
  })
	public Optional<Empresa> getById(@PathVariable Long id) {
  	 
  	Optional<Empresa> optional = empresaRepository.findById(id);
  	 
       return optional;
	}

}
