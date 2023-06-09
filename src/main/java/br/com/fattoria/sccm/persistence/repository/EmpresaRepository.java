package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Empresa;

@Repository
@RepositoryRestResource(collectionResourceRel = "empresas", path="empresas")
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
	
	Empresa findByNome(String nome);

}
