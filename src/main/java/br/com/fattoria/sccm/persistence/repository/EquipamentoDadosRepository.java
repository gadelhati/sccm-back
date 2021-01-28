package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.EquipamentoDados;
import br.com.fattoria.sccm.persistence.model.EquipamentoDadosPK;

@Repository
@RepositoryRestResource(collectionResourceRel = "equipamento_dados", path="equipamento_dados")
public interface EquipamentoDadosRepository extends CrudRepository<EquipamentoDados, EquipamentoDadosPK>{

}
