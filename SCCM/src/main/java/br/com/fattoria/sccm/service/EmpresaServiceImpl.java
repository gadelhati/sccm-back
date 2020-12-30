package br.com.fattoria.sccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fattoria.sccm.entidade.Empresa;
import br.com.fattoria.sccm.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private EmpresaRepository empresaRepository;
	
	@Autowired
	public EmpresaServiceImpl(EmpresaRepository empresaRepository) {		
		this.empresaRepository = empresaRepository;		
	}
	
	@Override
	public void cadastrar(Empresa empresa) {
		
		this.empresaRepository.save(empresa);
		
	}

}
