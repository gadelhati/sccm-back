package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.AssinaturaPC;

@Repository
@RepositoryRestResource(collectionResourceRel = "assinaturas", path="assinaturas", exported = false)
public interface AssinaturaPCRepository extends CrudRepository<AssinaturaPC, Long> {

	public List<AssinaturaPC> findByAtivo(Boolean ativo);
	
	
}
