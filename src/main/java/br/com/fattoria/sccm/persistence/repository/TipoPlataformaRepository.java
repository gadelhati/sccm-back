package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.TipoPlataforma;

@Repository
@RepositoryRestResource(collectionResourceRel = "tipos_plataforma", path = "tipos_plataforma")
public interface TipoPlataformaRepository extends PagingAndSortingRepository<TipoPlataforma, Long> {
	
	TipoPlataforma findByDescricao(String descricao);

}
