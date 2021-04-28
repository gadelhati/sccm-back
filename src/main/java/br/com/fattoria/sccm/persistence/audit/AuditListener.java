package br.com.fattoria.sccm.persistence.audit;

import java.util.Calendar;

import org.hibernate.envers.RevisionListener;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.fattoria.sccm.service.KeycloakAuthenticationTokenUtil;

public class AuditListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		AuditRevisionEntity revEntity = (AuditRevisionEntity) revisionEntity;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KeycloakPrincipal<?> authenticatedUser = (KeycloakPrincipal<?>) authentication.getPrincipal();

		AccessToken accessToken = KeycloakAuthenticationTokenUtil.getAccessToken(authenticatedUser);
		
		if(accessToken != null) {
			revEntity.setUsuario(accessToken.getPreferredUsername());
		}
		revEntity.setData(Calendar.getInstance());
	}

}
