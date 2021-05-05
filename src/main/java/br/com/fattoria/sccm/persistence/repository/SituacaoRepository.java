package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Situacao;

@Repository
@RepositoryRestResource(collectionResourceRel = "situacoes", path="situacoes", exported = false)
public interface SituacaoRepository extends CrudRepository<Situacao, Long> {

}
