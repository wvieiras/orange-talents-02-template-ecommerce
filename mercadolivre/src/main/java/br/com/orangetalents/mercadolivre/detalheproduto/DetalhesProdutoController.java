package br.com.orangetalents.mercadolivre.detalheproduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.orangetalents.mercadolivre.produto.Produto;

@RestController
public class DetalhesProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping("/produtos/{id}")
	public DetalheProdutoView getDetalhes(@PathVariable("id") Long id) {
		
		Produto produto = manager.find(Produto.class, id); 
		
		return new DetalheProdutoView(produto);
	}
}
