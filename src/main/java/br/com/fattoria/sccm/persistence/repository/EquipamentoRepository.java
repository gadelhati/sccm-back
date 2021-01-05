package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Equipamento;

@Repository
@RepositoryRestResource(collectionResourceRel = "equipamentos", path="equipamentos", exported = false)
public interface EquipamentoRepository extends PagingAndSortingRepository<Equipamento, Long> {
	
	Equipamento findByDescricao(String descricao);

}