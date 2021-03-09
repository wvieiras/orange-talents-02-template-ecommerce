package br.com.orangetalents.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.orangetalents.mercadolivre.usuario.Usuario;
import br.com.orangetalents.mercadolivre.usuario.UsuarioRepository;

@RestController
public class ProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}
	
	@PostMapping("/produtos")
	@Transactional
	public String post (@RequestBody @Valid NovoProdutoRequest request) {
		Usuario dono = usuarioRepository.findByEmail("wellington@teste.com").get();
		Produto produto = request.toModel(manager,dono);
		
		manager.persist(produto);
		return produto.toString();
	}
	
}
