package br.com.fattoria.sccm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.persistence.model.Usuario;
import br.com.fattoria.sccm.persistence.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(value = "Usuarios")
public class UsuariosController {

    private static final Logger log = LoggerFactory.getLogger(UsuariosController.class);

    private final HttpServletRequest request;
    private final UsuarioRepository usuarioRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public UsuariosController(HttpServletRequest request, UsuarioRepository usuarioRepository) {
        this.request = request;
        this.usuarioRepository = usuarioRepository;
    }

    @ApiOperation(value = "Retorna uma lista de usuarios")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de usuarios"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> getAllUsers() {
    	 
    	 List<Usuario> lista = (List<Usuario>) usuarioRepository.findAll();
    	 
         return lista;
	}

}
