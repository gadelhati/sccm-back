package br.com.fattoria.sccm.persistence.audit;

import org.hibernate.envers.RevisionListener;
import org.hibernate.envers.query.AuditEntity;

public class AuditListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		AuditEntity revEntity = (AuditEntity) revisionEntity;
		//revEntity.setUsuario(SessionBean.getUserName());
		//revEntity.setIp(SessionBean.getIP());
	}

}
