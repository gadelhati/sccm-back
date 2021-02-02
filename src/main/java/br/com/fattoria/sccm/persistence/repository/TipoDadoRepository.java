package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.TipoDado;

@Repository
@RepositoryRestResource(collectionResourceRel = "tipos_dado", path="tipos_dado", exported = false)
public interface TipoDadoRepository extends CrudRepository<TipoDado, Long> {
	
	TipoDado findByDescricao(String descricao);
	
	List<TipoDado> findAllByAtivoTrue();

}
