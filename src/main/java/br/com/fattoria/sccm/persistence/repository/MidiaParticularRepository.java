package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.MidiaParticular;

@Repository
@RepositoryRestResource(collectionResourceRel = "midias_particulares", path="midias_particulares", exported = false)
public interface MidiaParticularRepository extends CrudRepository<MidiaParticular, Long> {

}
