package br.com.orangetalents.mercadolivre.compra;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.orangetalents.mercadolivre.produto.Produto;
import br.com.orangetalents.mercadolivre.usuario.Usuario;
import br.com.orangetalents.mercadolivre.usuario.UsuarioRepository;

@RestController
public class FechamentoCompraController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/compras")
	@Transactional
	public String post(@RequestBody @Valid NovaCompraRequest request, UriComponentsBuilder uriComponentsBuilder) throws BindException {
		
		Produto produtoASerComprado =  manager.find(Produto.class, request.getIdProduto());
		
		int quantidade = request.getQuantidade();
		
		boolean abateu = produtoASerComprado.abataEstoque(quantidade);
		if(abateu) {
			Usuario comprador = usuarioRepository.findByEmail("wellington@teste.com").get();
			GatewayPagamento gateway = request.getGateway();
			
			Compra novaCompra =  new Compra(produtoASerComprado, quantidade, comprador,gateway);
			manager.persist(novaCompra);
			
			if(gateway.equals(GatewayPagamento.pagseguro)) {
				String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
					.buildAndExpand(novaCompra.getId()).toString();
				
				return "pagseguro.com/" + novaCompra.getId()
						+"?redirectUrl="+urlRetornoPagseguro;
			} else {
				String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
						.buildAndExpand(novaCompra.getId()).toString();
					
					return "paypal.com/" + novaCompra.getId()
							+"?redirectUrl="+urlRetornoPaypal;
			}
		}
		
		//Utilizando o BindException como ferramenta para mapear para o retorno que quero.
		//Utilizei Exception para controlar Fluxo.
		BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque");
		
		throw problemaComEstoque;
		
	}
}
