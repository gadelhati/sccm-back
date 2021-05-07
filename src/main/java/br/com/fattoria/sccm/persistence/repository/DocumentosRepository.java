package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Documento;

@Repository
@RepositoryRestResource(collectionResourceRel = "documentos", path="documentos", exported = false)
public interface DocumentosRepository extends CrudRepository<Documento, Long> {

	@Query("select d from Documento d where d.controleInterno.id =:id")
	Collection<Documento> findAllByControleInternoId(@Param("id") Long id);

	@Query("select d from Documento d where d.controleInterno.pesquisaCientifica.id =:id")
	Collection<Documento> findAllByPesquisaCientificaId(@Param("id") Long id);

}
