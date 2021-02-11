package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.PesquisaCientificaEquipamento;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaEquipamentoPk;

@Repository
@RepositoryRestResource(collectionResourceRel = "pesquisas_cientificas_equipamentos", path="pesquisas_cientificas_equipamentos", exported = false)
public interface PesquisaCientificaEquipamentoRepository extends CrudRepository<PesquisaCientificaEquipamento, PesquisaCientificaEquipamentoPk> {

}
