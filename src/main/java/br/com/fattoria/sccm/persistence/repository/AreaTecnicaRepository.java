package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.AreaTecnica;

@Repository
@RepositoryRestResource(collectionResourceRel = "areaTecnica", path="areaTecnica")
public interface AreaTecnicaRepository extends CrudRepository<AreaTecnica, Long> {

	Collection<AreaTecnica> findAllByAtivoTrue();

}
