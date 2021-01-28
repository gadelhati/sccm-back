package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Pais;

@Repository
@RepositoryRestResource(collectionResourceRel = "pais", path="pais")
public interface PaisRepository extends CrudRepository<Pais, Long>{

}
