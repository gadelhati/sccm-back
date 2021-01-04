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

import br.com.fattoria.sccm.persistence.model.Instituicao;
import br.com.fattoria.sccm.persistence.repository.InstituicaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(value = "Instituições")
@RequestMapping(value = "/instituicoes")
public class InstituicoesController implements CRUDApi<Instituicao, Long> {

    private static final Logger log = LoggerFactory.getLogger(InstituicoesController.class);

    private final InstituicaoRepository instituicaoRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public InstituicoesController(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    @RolesAllowed("user")
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma lista de instituições")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de instituicoes"),
    })
	public List<Instituicao> getAll() {
    	 
    	 List<Instituicao> lista = (List<Instituicao>) instituicaoRepository.findAll();
    	 
         return lista;
	}
    
    @RolesAllowed("user")
    @GetMapping(value = "/instituicoes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma instituição")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma instituição"),
    })
	public Optional<Instituicao> getById(@PathVariable Long id) {
    	 
    	Optional<Instituicao> optional = instituicaoRepository.findById(id);
    	 
         return optional;
	}
    
    @RolesAllowed("user")
    @PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Salva uma instituição")
    public Instituicao save(@Valid @RequestBody Instituicao instituicao){
    	return instituicaoRepository.save(instituicao);
    }
    
    @RolesAllowed("user")
    @DeleteMapping(value = "/deletar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta uma instituição")
    public void delete(@PathVariable Long id){
    	instituicaoRepository.deleteById(id);
    }

}
