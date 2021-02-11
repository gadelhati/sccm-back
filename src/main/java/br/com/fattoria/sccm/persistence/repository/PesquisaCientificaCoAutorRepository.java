package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.fattoria.sccm.persistence.model.PesquisaCientificaCoAutor;
import br.com.fattoria.sccm.persistence.model.PesquisaCientificaCoAutorPk;

public interface PesquisaCientificaCoAutorRepository extends CrudRepository<PesquisaCientificaCoAutor, PesquisaCientificaCoAutorPk> {

}
