package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Plataforma;

@Repository
@RepositoryRestResource(collectionResourceRel = "plataformas", path="plataformas")
public interface PlataformaRepository extends CrudRepository<Plataforma, Long> {
	
	Plataforma findByNome(String nome);
	
	List<Plataforma> findAllByAtivoTrue();
	
	@RestResource(exported = false)
	@Override
	<S extends Plataforma> S save(S entity);
}
