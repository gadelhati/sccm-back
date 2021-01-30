package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.MetodoAmostragem;

@Repository
@RepositoryRestResource(collectionResourceRel = "metodoAmostragem", path="metodoAmostragem")
public interface MetodoAmostragemRepository extends CrudRepository<MetodoAmostragem, Long> {

}
