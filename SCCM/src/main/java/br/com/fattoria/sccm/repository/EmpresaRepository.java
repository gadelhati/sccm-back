package br.com.fattoria.sccm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.entidade.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
