package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.ShipSynop;

@Repository
@RepositoryRestResource(collectionResourceRel = "shipSynop", path="shypSynop", exported = false)
public interface ShipSynopRepository extends CrudRepository<ShipSynop, Long> {

	public Collection<ShipSynop> findByDados(String dados);
	
}
