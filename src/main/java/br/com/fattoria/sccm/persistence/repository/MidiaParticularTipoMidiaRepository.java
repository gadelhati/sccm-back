package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.MidiaParticularTipoMidia;
import br.com.fattoria.sccm.persistence.model.MidiaParticularTipoMidiaPK;

@Repository
@RepositoryRestResource(collectionResourceRel = "midias_particulares_tipos_midias", path="midias_particulares_tipos_midias", exported = false)
public interface MidiaParticularTipoMidiaRepository extends CrudRepository<MidiaParticularTipoMidia, MidiaParticularTipoMidiaPK> {

	@Query("select mptm from MidiaParticularTipoMidia mptm where mptm.midiaParticular.id =:id")
	List<MidiaParticularTipoMidia> findAllByMidiaParticularId(@Param("id") Long id);
	

}
