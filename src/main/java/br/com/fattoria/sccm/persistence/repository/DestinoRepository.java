package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Destino;

@Repository
@RepositoryRestResource(collectionResourceRel = "destinos", path="destinos", exported = false)
public interface DestinoRepository extends CrudRepository<Destino, Long> {

}
