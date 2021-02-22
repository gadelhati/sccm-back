package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.MidiaDiversaTipoMidia;
import br.com.fattoria.sccm.persistence.model.MidiaDiversaTipoMidiaPK;

@Repository
@RepositoryRestResource(collectionResourceRel = "midias_diversas_tipos_midias", path="midias_diversas_tipos_midias", exported = false)
public interface MidiaDiversaTipoMidiaRepository extends CrudRepository<MidiaDiversaTipoMidia, MidiaDiversaTipoMidiaPK> {

	@Query("select mdtm from MidiaDiversaTipoMidia mdtm where mdtm.midiaDiversa.id =:id")
	List<MidiaDiversaTipoMidia> findAllByMidiaDiversaId(@Param("id") Long id);
	

}
