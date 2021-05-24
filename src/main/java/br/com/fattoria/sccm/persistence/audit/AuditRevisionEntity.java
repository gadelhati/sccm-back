package br.com.fattoria.sccm.persistence.audit;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name="revinfo")
@RevisionEntity(AuditListener.class)
public class AuditRevisionEntity extends DefaultRevisionEntity {
	
	private static final long serialVersionUID = 1L;
	
	public String usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

}
