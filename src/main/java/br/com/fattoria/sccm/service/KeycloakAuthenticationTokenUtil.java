package br.com.fattoria.sccm.service;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeycloakAuthenticationTokenUtil {
	
	private final static Logger log = LoggerFactory.getLogger(KeycloakAuthenticationTokenUtil.class);
	
	public static AccessToken getAccessToken(HttpServletRequest request){
    	KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();   
    	log.info("Token "+token.toString());
        KeycloakPrincipal<?> keycloakPrincipal = (KeycloakPrincipal<?>) token.getPrincipal();
        log.info("Principal "+keycloakPrincipal.getName());
        KeycloakSecurityContext session = keycloakPrincipal.getKeycloakSecurityContext();
        log.info("Token String "+session.getTokenString());
        AccessToken accessToken = session.getToken();
        log.info("Username "+accessToken.getPreferredUsername());
        return accessToken;
	}
	
	public static AccessToken getAccessToken(Principal principal){
		if(principal != null) {
	        KeycloakPrincipal<?> keycloakPrincipal = (KeycloakPrincipal<?>) principal;
	        log.info("Principal "+keycloakPrincipal.getName());
	        KeycloakSecurityContext session = keycloakPrincipal.getKeycloakSecurityContext();
	        log.info("Token String "+session.getTokenString());
	        AccessToken accessToken = session.getToken();
	        log.info("Username "+accessToken.getPreferredUsername());
	        return accessToken;
		}else {
			return null;
		}
	}

}
