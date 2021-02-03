package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.TipoEndereco;

@Repository
@RepositoryRestResource(collectionResourceRel = "tipoEndereco", path="tipoEndereco", exported = false)
public interface TipoEnderecoRepository extends CrudRepository<TipoEndereco, Long> {

}
