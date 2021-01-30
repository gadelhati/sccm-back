package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.TipoPlataforma;

@Repository
@RepositoryRestResource(collectionResourceRel = "tipos_plataforma", path="tipos_plataforma", exported = false)
public interface TipoPlataformaRepository extends CrudRepository<TipoPlataforma, Long> {
	
	TipoPlataforma findByDescricao(String descricao);
	
	List<TipoPlataforma> findAllByAtivoTrue();

}
