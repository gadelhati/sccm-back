package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.AreaConhecimento;

@Repository
@RepositoryRestResource(collectionResourceRel = "areas_conhecimento", path="areas_conhecimento")
public interface AreaConhecimentoRepository extends CrudRepository<AreaConhecimento, Long> {
	
	AreaConhecimento findByDescricao(String descricao);
	
	List<AreaConhecimento> findAllByAtivoTrue();

}
