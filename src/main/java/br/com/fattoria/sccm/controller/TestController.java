package br.com.fattoria.sccm.controller;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fattoria.sccm.api.UsuarioApi;
import br.com.fattoria.sccm.service.KeycloakAuthenticationTokenUtil;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class TestController {
	
	private final Logger log = LoggerFactory.getLogger(TestController.class);
	
	private final HttpServletRequest request;
	
    public TestController(HttpServletRequest request) {
		super();
		this.request = request;
	}

	@GetMapping("/anonymous")
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Hello Anonymous");
    }

    @RolesAllowed("user")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Hello User");
    }

    @RolesAllowed("admin")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }

    @RolesAllowed({ "admin", "user" })
    @RequestMapping(value = "/all-user", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUser() {
        return ResponseEntity.ok("Hello All User");
    }

    @RequestMapping(value = "/usuario/1", method = RequestMethod.GET)
    public ResponseEntity<UsuarioApi> getUserDetails(HttpServletRequest request) {
    	
    	AccessToken accessToken = KeycloakAuthenticationTokenUtil.getAccessToken(request);
    	
    	log.info("NOME "+getKeycloakSecurityContext().getToken().getPreferredUsername());
    	
    	
        
        return ResponseEntity.ok(new UsuarioApi(accessToken.getId(), accessToken.getPreferredUsername()));
    }
    
    private KeycloakSecurityContext getKeycloakSecurityContext()
    {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }

}
