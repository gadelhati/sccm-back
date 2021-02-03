package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.AreaConhecimentoEquipamento;
import br.com.fattoria.sccm.persistence.model.TipoDadoEquipamento;
import br.com.fattoria.sccm.persistence.model.TipoDadoEquipamentoPK;

@Repository
@RepositoryRestResource(collectionResourceRel = "areas_conhecimento_equipamento", path="areas_conhecimento_equipamento")
public interface TipoDadoEquipamentoRepository extends CrudRepository<TipoDadoEquipamento, TipoDadoEquipamentoPK> {

	@Query("select ac from TipoDadoEquipamento ac where ac.equipamento.id =:id")
	List<AreaConhecimentoEquipamento> findAllByEquipamentoId(@Param("id") Long id);

}
