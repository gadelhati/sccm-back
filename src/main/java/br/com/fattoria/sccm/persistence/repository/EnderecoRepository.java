package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Endereco;

@Repository
@RepositoryRestResource(collectionResourceRel = "enderecos", path="enderecos", exported = false)
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
	
	@Query("select i.endereco from Instituicao i where i.id =:idInstituicao")
	Endereco getEnderecoByIdInstituicao(@Param("idInstituicao") Long idInstituicao);
	
}
