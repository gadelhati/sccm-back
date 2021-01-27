package br.com.fattoria.sccm.persistence.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.fattoria.sccm.persistence.model.Sigilo;

@Component("beforeCreateSigiloValidator")
public class SigiloValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Sigilo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Sigilo sigilo = (Sigilo) target;
		
		if (checkUniqueDescription(sigilo.getDescricao())) {
			errors.reject("descricao", "Já existe uma descrição com esse nome");
		}
		
	}
	
	private boolean checkUniqueDescription(String description) {
		return (description == null || description.trim().length() == 0);
	}

	
	
}
