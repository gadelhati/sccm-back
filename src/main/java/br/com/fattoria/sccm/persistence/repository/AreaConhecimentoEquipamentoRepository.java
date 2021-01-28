package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.AreaConhecimentoEquipamento;
import br.com.fattoria.sccm.persistence.model.AreaConhecimentoEquipamentoPK;

@Repository
@RepositoryRestResource(collectionResourceRel = "areas_conhecimento_equipamento", path="areas_conhecimento_equipamento")
public interface AreaConhecimentoEquipamentoRepository extends CrudRepository<AreaConhecimentoEquipamento, AreaConhecimentoEquipamentoPK> {

}
