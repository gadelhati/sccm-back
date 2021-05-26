package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.ShipSynop;

@Repository
@RepositoryRestResource(collectionResourceRel = "shipSynop", path="shypSynop", exported = false)
public interface ShipSynopRepository extends CrudRepository<ShipSynop, Long> {

	public Collection<ShipSynop> findByDados(String dados);
	
	public Page<ShipSynop> findAllByDados(Pageable page, @Param("dados") String dados);

	@Query("select s from ShipSynop s where s.dados =:dados and "
		+ "("
		+ "UPPER(s.comissao.nomeComissao) like UPPER(:search) or "
		+ "UPPER(s.plataforma.nome) like UPPER(:search) or "
		+ "UPPER(s.plataforma.indicativoVisual) like UPPER(:search) or "
		+ "UPPER(s.protocolo) like UPPER(:search) "
		+ ")")
	public Page<ShipSynop> findAllBySearch(Pageable page, @Param("search") String search, @Param("dados") String dados);
	
}
