package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.TipoDado;

@Repository
@RepositoryRestResource(collectionResourceRel = "tipos_dado", path="tipos_dado", exported = false)
public interface TipoDadoRepository extends CrudRepository<TipoDado, Long> {
	
	TipoDado findByDescricao(String descricao);
	
	List<TipoDado> findAllByAtivoTrue();
	
	@Query("select ac.tipoDado from EquipamentoDados ac where ac.equipamento.id =:id")
	List<TipoDado> findAllByEquipamentoId(@Param("id") Long id);
	
	@Query("select pcd.tipoDado from PesquisaCientificaDados pcd where pcd.pesquisaCientifica.id =:id")
	List<TipoDado> findAllByPesquisaCientificaId(@Param("id") Long id);

}
