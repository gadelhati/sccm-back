package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;

@Repository
@RepositoryRestResource(collectionResourceRel = "pesquisas_cientificas", path="pesquisas_cientificas", exported = false)
public interface PesquisaCientificaRepository extends CrudRepository<PesquisaCientifica, Long> {

}
