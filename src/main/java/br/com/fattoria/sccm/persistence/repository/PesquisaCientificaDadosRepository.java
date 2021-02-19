package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.PesquisaCientificaDados;

@Repository
@RepositoryRestResource(collectionResourceRel = "pesquisaCientificaDados", path="pesquisaCientificaDados", exported = false)
public interface PesquisaCientificaDadosRepository extends CrudRepository<PesquisaCientificaDados, Long> {

	@Query("select pcd from PesquisaCientificaDados pcd where pcd.pesquisaCientifica.id =:id")
	Collection<PesquisaCientificaDados> findAllByIdPesquisaCientifica(@Param("id") Long idPesquisaCientifica);

}
