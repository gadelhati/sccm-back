package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.ControleInterno;

@Repository
@RepositoryRestResource(collectionResourceRel = "controleInterno", path="controleInterno", exported = false)
public interface ControleInternoRepository extends CrudRepository<ControleInterno, Long> {

	@Query("select ci from ControleInterno ci where ci.pesquisaCientifica.id =:id")
	Collection<ControleInterno> findAllByPesquisaCientificaId(@Param("id") Long id);
	
}
