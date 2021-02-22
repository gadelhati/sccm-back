package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.PesquisaCientificaAreaConhecimento;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaAreaConhecimentoPk;

@Repository
@RepositoryRestResource(collectionResourceRel = "pesquisas_cientificas_areas_conhecimento", path="pesquisas_cientificas_areas_conhecimento", exported = false)
public interface PesquisaCientificaAreaConhecimentoRepository extends CrudRepository<PesquisaCientificaAreaConhecimento, PesquisaCientificaAreaConhecimentoPk> {

	@Query("select pcac from PesquisaCientificaAreaConhecimento pcac where pcac.pesquisaCientifica.id =:id")
	Collection<PesquisaCientificaAreaConhecimento> findAllByIdPesquisaCientifica(@Param("id") Long idPesquisaCientifica);
	
}
