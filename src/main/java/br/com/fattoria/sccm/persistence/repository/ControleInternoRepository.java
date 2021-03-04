package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.ControleInterno;

@Repository
@RepositoryRestResource(collectionResourceRel = "controleInterno", path="controleInterno", exported = false)
public interface ControleInternoRepository extends CrudRepository<ControleInterno, Long> {
	
}
