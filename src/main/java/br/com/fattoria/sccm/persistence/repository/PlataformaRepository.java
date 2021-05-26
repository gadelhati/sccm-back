package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Plataforma;

@Repository
@RepositoryRestResource(collectionResourceRel = "plataformas", path="plataformas", exported = false)
public interface PlataformaRepository extends CrudRepository<Plataforma, Long> {
	
	Plataforma findByNome(String nome);
	
	List<Plataforma> findAllByAtivoTrue();
	
	Page<Plataforma> findAll(Pageable page);

	@Query("select p from Plataforma p where "
		+ "(UPPER(p.nome) like UPPER(:search) or UPPER(p.indicativoVisual) like UPPER(:search) or UPPER(p.tipoPlataforma.descricao) like UPPER(:search))")
	Page<Plataforma> findAllBySearch(Pageable page, @Param("search") String search);
	
}
