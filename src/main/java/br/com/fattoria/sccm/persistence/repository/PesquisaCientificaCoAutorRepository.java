package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.fattoria.sccm.persistence.model.PesquisaCientificaCoAutor;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaCoAutorPk;

public interface PesquisaCientificaCoAutorRepository extends CrudRepository<PesquisaCientificaCoAutor, PesquisaCientificaCoAutorPk> {

	@Query("select pcca from PesquisaCientificaCoAutor pcca where pcca.pesquisaCientifica.id =:id")
	Collection<PesquisaCientificaCoAutor> findAllByIdPesquisaCientifica(@Param("id") Long idPesquisaCientifica);

}
