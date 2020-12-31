package br.com.fattoria.sccm.persistence.audit;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity(name="revinfo")
@RevisionEntity(AuditListener.class)
public class AuditRevisionEntity extends DefaultRevisionEntity {
	
	private static final long serialVersionUID = 1L;
	
	public String usuario;
	public String ip;

}
