package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Instituicao;

@Repository
@RepositoryRestResource(collectionResourceRel = "instituicoes", path="instituicoes", exported = false)
public interface InstituicaoRepository extends CrudRepository<Instituicao, Long> {
	
	Instituicao findByNome(String nome);

}
