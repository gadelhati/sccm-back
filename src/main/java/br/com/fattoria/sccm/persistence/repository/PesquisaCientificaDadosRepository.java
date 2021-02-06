package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.PesquisaCientificaDados;

@Repository
@RepositoryRestResource(collectionResourceRel = "pesquisaCientificaDados", path="pesquisaCientificaDados", exported = false)
public interface PesquisaCientificaDadosRepository extends CrudRepository<PesquisaCientificaDados, Long> {

}
