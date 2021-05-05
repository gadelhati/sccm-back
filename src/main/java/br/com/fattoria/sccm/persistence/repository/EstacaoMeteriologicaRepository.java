package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.EstacaoMeteriologica;

@Repository
@RepositoryRestResource(collectionResourceRel = "estacoes_meteriologicas", path="estacoes_meteriologicas", exported = false)
public interface EstacaoMeteriologicaRepository extends CrudRepository<EstacaoMeteriologica, Long> {

}
