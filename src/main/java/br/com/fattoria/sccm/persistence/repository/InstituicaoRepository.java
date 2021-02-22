package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Instituicao;

@Repository
@RepositoryRestResource(collectionResourceRel = "instituicoes", path="instituicoes", exported = false)
public interface InstituicaoRepository extends CrudRepository<Instituicao, Long> {
	
	List<Instituicao> findAllByAtivoTrue();
	
	@Query("select i from Instituicao i where i.ativo=true and i.tipoInstituicao='PRIVADO'")
	List<Instituicao> findAllByTipoInstituicaoPrivadoAndAtivoTrue();
	
	@Query("select i from Instituicao i where i.ativo=true and i.tipoInstituicao='PUBLICO'")
	List<Instituicao> findAllByTipoInstituicaoPublicoAndAtivoTrue();

	@Query("select pcca.instituicao from PesquisaCientificaCoAutor pcca where pcca.pesquisaCientifica.id =:id")
	Collection<Instituicao> findAllByPesquisaCientificaId(@Param("id") Long id);

}
