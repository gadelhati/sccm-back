package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.MidiaParticular;

@Repository
@RepositoryRestResource(collectionResourceRel = "midias_particulares", path="midias_particulares", exported = false)
public interface MidiaParticularRepository extends CrudRepository<MidiaParticular, Long> {

	Page<MidiaParticular> findAll(Pageable page);

	@Query("select m from MidiaParticular m where "
		+ "(UPPER(m.comissaoProjeto) like UPPER(:search) or "
		+ "UPPER(m.plataforma.nome) like UPPER(:search) or "
		+ "UPPER(m.plataforma.indicativoVisual) like UPPER(:search) or "
		+ "UPPER(m.instituicao.nome) like UPPER(:search) or "
		+ "UPPER(m.instituicao.sigla) like UPPER(:search) or "
		+ "UPPER(m.numeroFicha) like UPPER(:search) or "
		+ "CAST(m.numeroRA as text) like UPPER(:search) or "
		+ "CAST(m.numeroAutorizacao as text) like UPPER(:search) or "
		+ "UPPER(m.situacao.descricao) like UPPER(:search))")
	Page<MidiaParticular> findAllBySearch(Pageable page, @Param("search") String search);
	
}
