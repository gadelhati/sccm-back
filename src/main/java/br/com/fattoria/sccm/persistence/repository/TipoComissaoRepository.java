package br.com.fattoria.sccm.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.TipoComissao;

@Repository
@RepositoryRestResource(collectionResourceRel = "tipos_comissao", path="tipos_comissao")
public interface TipoComissaoRepository extends CrudRepository<TipoComissao, Long> {
	
	List<TipoComissao> findAllByAtivoTrue();

}
