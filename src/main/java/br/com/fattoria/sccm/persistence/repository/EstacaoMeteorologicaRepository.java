package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.EstacaoMeteorologica;

@Repository
@RepositoryRestResource(collectionResourceRel = "estacoes_meteolologicas", path="estacoes_meteolologicas", exported = false)
public interface EstacaoMeteorologicaRepository extends CrudRepository<EstacaoMeteorologica, Long> {

}
