package br.estacio.tcc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.estacio.tcc.controller.ClienteController;
import br.estacio.tcc.modelo.Cliente;

@Component
public class BuscaClienteValidator implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(BuscaClienteValidator.class);

	@Override
	public boolean supports(Class<?> classe) {
		return Cliente.class.isAssignableFrom(classe);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cliente cliente = (Cliente) target;
		String nome = cliente.getNome();
		if(!StringUtils.hasLength(nome)){
			errors.rejectValue("nome", "required", "Preencha o nome.");
		} else if (nome.trim().length() < 3) {
			errors.rejectValue("nome", "tooShort", "Preencha pelo menos 3 letras.");
		}
		
	}

}
