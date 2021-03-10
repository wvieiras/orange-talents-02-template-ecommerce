package br.com.orangetalents.mercadolivre.opniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.orangetalents.mercadolivre.produto.Produto;
import br.com.orangetalents.mercadolivre.usuario.Usuario;
import br.com.orangetalents.mercadolivre.usuario.UsuarioRepository;

@RestController
public class OpiniaoController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/produtos/{id}/opiniao")
	@Transactional
	public String post(@RequestBody @Valid NovaOpiniaoRequest request, @PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		Usuario dono = usuarioRepository.findByEmail("wellington@teste.com").get();
		Opiniao novaOpiniao = request.toModel(produto, dono);
		
		manager.persist(novaOpiniao);
		
		return novaOpiniao.toString();
	}
}
