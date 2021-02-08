package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Comissao;

@Repository
@RepositoryRestResource(collectionResourceRel = "comissoes", path="comissoes", exported = false)
public interface ComissaoRepository extends CrudRepository<Comissao, Long> {

}
