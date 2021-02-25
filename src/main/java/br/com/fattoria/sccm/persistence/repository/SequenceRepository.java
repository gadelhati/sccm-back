package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Sequence;

@Repository
@RepositoryRestResource(collectionResourceRel = "sequence", path="sequence")
public interface SequenceRepository extends CrudRepository<Sequence, Long> {

}
