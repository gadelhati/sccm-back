package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.EstacaoMeteorologica;

@Repository
@RepositoryRestResource(collectionResourceRel = "estacoes_meteorologicas", path="estacoes_meteorologicas", exported = false)
public interface EstacaoMeteorologicaRepository extends CrudRepository<EstacaoMeteorologica, Long> {
	
	List<EstacaoMeteorologica> findAllByAtivoTrue();
	
	Page<EstacaoMeteorologica> findAll(Pageable page);

	@Query("select p from EstacaoMeteorologica p where "
		+ "(UPPER(p.nome) like UPPER(:search))")
	Page<EstacaoMeteorologica> findAllBySearch(Pageable page, @Param("search") String search);

}
