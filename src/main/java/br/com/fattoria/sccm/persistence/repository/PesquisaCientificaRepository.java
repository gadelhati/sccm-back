package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;

@Repository
@RepositoryRestResource(collectionResourceRel = "pesquisas_cientificas", path="pesquisas_cientificas", exported = false)
public interface PesquisaCientificaRepository extends CrudRepository<PesquisaCientifica, Long> {
	
	Collection<PesquisaCientifica> findAll(Sort sort);
	
	Page<PesquisaCientifica> findAll(Pageable page);

	@Query("select pc from PesquisaCientifica pc where "
			+ "(pc.comissao.nomeComissao like :search or pc.instituicao.nome like :search "
			+ "or pc.instituicao.sigla like :search or pc.plataforma.nome like :search "
			+ "or pc.comandante like :search or pc.coordenadorCientifico like :search or pc.numeroPC like :search)")
	Page<PesquisaCientifica> findAllBySearch(Pageable page, @Param("search") String search);

}
