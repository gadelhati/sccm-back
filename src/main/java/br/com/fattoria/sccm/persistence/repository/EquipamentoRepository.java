package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Equipamento;

@Repository
@RepositoryRestResource(collectionResourceRel = "equipamentos", path="equipamentos", exported = false)
public interface EquipamentoRepository extends CrudRepository<Equipamento, Long> {
	
	List<Equipamento> findAllByAtivoTrue();
	
	@Query("select pce.equipamento from PesquisaCientificaEquipamento pce where pce.pesquisaCientifica.id =:id")
	List<Equipamento> findAllByPesquisaCientificaId(@Param("id") Long id);
	
}
