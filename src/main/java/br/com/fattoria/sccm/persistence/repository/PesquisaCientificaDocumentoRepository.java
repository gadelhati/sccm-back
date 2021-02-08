package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.PesquisaCientificaDocumento;

@Repository
@RepositoryRestResource(collectionResourceRel = "pesquisas_cientificas_documentos", path="pesquisas_cientificas_documentos", exported = false)
public interface PesquisaCientificaDocumentoRepository extends CrudRepository<PesquisaCientificaDocumento, Long> {

}
