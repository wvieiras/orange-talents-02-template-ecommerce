package br.com.orangetalents.mercadolivre.produto;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors()) {
			return ;
		}
		
		NovoProdutoRequest request = (NovoProdutoRequest) target;
		Set<String> nomesIguais = request.buscarCaracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "O produto tem caracteristicas Iguais"+nomesIguais);
		}

	}

}
