package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Empresa;

@Repository
@RepositoryRestResource(collectionResourceRel = "empresas", path = "empreasas")
public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, Long> {
	
	Empresa findByNome(String nome);

}
