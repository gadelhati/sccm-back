package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.PalavraChave;

@Repository
@RepositoryRestResource(collectionResourceRel = "palavraChave", path="palavraChave", exported = false)
public interface PalavraChaveRepository extends CrudRepository<PalavraChave, Long> {

}
