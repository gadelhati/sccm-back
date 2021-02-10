package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Sigilo;

@Repository
@RepositoryRestResource(collectionResourceRel = "sigilos", path="sigilos")
public interface SigiloRepository extends PagingAndSortingRepository<Sigilo, Long>{
	
	List<Sigilo> findAllByAtivoTrue();

}
