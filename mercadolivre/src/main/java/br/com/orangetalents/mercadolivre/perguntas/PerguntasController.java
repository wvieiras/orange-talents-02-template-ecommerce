package br.com.orangetalents.mercadolivre.perguntas;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.orangetalents.mercadolivre.produto.Produto;
import br.com.orangetalents.mercadolivre.usuario.Usuario;
import br.com.orangetalents.mercadolivre.usuario.UsuarioRepository;

@RestController
public class PerguntasController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping("/produtos/{id}/perguntas")
	@Transactional
	public String post(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class,id);
		Usuario interessado = usuarioRepository.findByEmail("wellington@teste.com").get();
		Pergunta pergunta = request.toModel(interessado, produto);
		
		manager.persist(pergunta);
		
		return pergunta.toString();
		
	}
}
