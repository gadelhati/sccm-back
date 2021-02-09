package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.MidiaDiversa;

@Repository
@RepositoryRestResource(collectionResourceRel = "midias_diversas", path="midias_diversas", exported = false)
public interface MidiaDiversaRepository extends CrudRepository<MidiaDiversa, Long> {

}
