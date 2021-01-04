package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Plataforma;

@Repository
@RepositoryRestResource(collectionResourceRel = "plataformas", path = "plataformas")
public interface PlataformaRepository extends PagingAndSortingRepository<Plataforma, Long> {
	
	Plataforma findByNome(String nome);

}
