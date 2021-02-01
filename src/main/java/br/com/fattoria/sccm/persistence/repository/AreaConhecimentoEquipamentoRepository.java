package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.AreaConhecimentoEquipamento;
import br.com.fattoria.sccm.persistence.model.AreaConhecimentoEquipamentoPK;

@Repository
@RepositoryRestResource(collectionResourceRel = "areas_conhecimento_equipamento", path="areas_conhecimento_equipamento")
public interface AreaConhecimentoEquipamentoRepository extends CrudRepository<AreaConhecimentoEquipamento, AreaConhecimentoEquipamentoPK> {

	@Query("select ac from AreaConhecimentoEquipamento ac where ac.areaConhecimento.id =:id")
	List<AreaConhecimentoEquipamento> findAllByIdEquipamento(@Param("id") Long id);

}
