package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.TipoAnexo;

@Repository
@RepositoryRestResource(collectionResourceRel = "tipoAnexo", path="tipoAnexo", exported = false)
public interface TipoAnexoRepository extends CrudRepository<TipoAnexo, Long> {

}
