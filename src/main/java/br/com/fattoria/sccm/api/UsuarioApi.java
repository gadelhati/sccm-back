package br.com.fattoria.sccm.api;

import java.security.Principal;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioApi {
	
	private String id;
	
	private String nome;
	
	public static UsuarioApi toUsuarioApi(final Principal principal){
		
		KeycloakAuthenticationToken kp = (KeycloakAuthenticationToken) principal;

        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) kp.getDetails();

        AccessToken token  = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();
        
        return new UsuarioApi(token.getId(), token.getPreferredUsername());
	}

}
