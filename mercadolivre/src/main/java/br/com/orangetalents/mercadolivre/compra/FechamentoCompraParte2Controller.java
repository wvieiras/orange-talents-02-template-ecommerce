package br.com.orangetalents.mercadolivre.compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FechamentoCompraParte2Controller {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/retorno-pagseguro/{id}")
	@Transactional
	public String processamentoPagseguro(@PathVariable("id") Long idCompra ,@RequestBody @Valid RetornoPagamentoRequest request) {
		 
		Compra compra = manager.find(Compra.class, idCompra);
		compra.adicionaTransacao(request);
		
		manager.merge(compra);
		
		return compra.toString();
	}
	
	@PostMapping("/retorno-paypal/{id}")
	@Transactional
	public String processamentoPaypal(@PathVariable("id") Long idCompra ,@RequestBody @Valid RetornoPaypalRequest request) {
		 
		Compra compra = manager.find(Compra.class, idCompra);
		compra.adicionaTransacao(request);
		
		manager.merge(compra);
		
		return compra.toString();
	}
}
