package br.com.orangetalents.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/produtos")
	public String post (@RequestBody @Valid NovoProdutoRequest request) {
		Usuario dono = usuarioRepository.findByEmail("wellington@teste.com").get();
		Produto produto = request.toModel(manager,dono);
		return produto.toString();
	}
	
}
