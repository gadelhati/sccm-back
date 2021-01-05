package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.TipoMidia;

@Repository
@RepositoryRestResource(collectionResourceRel = "tipos_midia", path="tipos_midia")
public interface TipoMidiaRepository extends PagingAndSortingRepository<TipoMidia, Long> {
	
	TipoMidia findByDescricao(String descricao);

}