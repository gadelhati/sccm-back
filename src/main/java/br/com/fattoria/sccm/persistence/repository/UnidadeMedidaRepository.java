package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.UnidadeMedida;

@Repository
@RepositoryRestResource(collectionResourceRel = "unidadeMedida", path="unidadeMedida")
public interface UnidadeMedidaRepository extends CrudRepository<UnidadeMedida, Long> {

}
