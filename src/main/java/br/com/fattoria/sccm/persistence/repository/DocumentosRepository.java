package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Documento;

@Repository
@RepositoryRestResource(collectionResourceRel = "documentos", path="documentos", exported = false)
public interface DocumentosRepository extends CrudRepository<Documento, Long> {

}
