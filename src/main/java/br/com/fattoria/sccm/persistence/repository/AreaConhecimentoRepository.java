package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.AreaConhecimento;

@Repository
@RepositoryRestResource(collectionResourceRel = "areas_conhecimento", path="areas_conhecimento")
public interface AreaConhecimentoRepository extends CrudRepository<AreaConhecimento, Long> {
	
	AreaConhecimento findByDescricao(String descricao);
	
	List<AreaConhecimento> findAllByAtivoTrue();
	
	@Query("select ac.areaConhecimento from AreaConhecimentoEquipamento ac where ac.equipamento.id =:id")
	List<AreaConhecimento> findAllByEquipamentoId(@Param("id") Long id);

	@Query("select pcac.areaConhecimento from PesquisaCientificaAreaConhecimento pcac where pcac.pesquisaCientifica.id =:id")
	List<AreaConhecimento> findAllByPesquisaCientificaId(@Param("id") Long id);

}
