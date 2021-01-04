package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.Usuario;

@Repository
@RepositoryRestResource(exported = false)
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

}
