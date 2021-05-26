package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.MidiaDiversa;

@Repository
@RepositoryRestResource(collectionResourceRel = "midias_diversas", path="midias_diversas", exported = false)
public interface MidiaDiversaRepository extends CrudRepository<MidiaDiversa, Long> {
	
	Page<MidiaDiversa> findAll(Pageable page);

	@Query("select m from MidiaDiversa m where "
		+ "(UPPER(m.numeroOficio) like UPPER(:search) or UPPER(m.situacao.descricao) like UPPER(:search))")
	Page<MidiaDiversa> findAllBySearch(Pageable page, @Param("search") String search);

}
