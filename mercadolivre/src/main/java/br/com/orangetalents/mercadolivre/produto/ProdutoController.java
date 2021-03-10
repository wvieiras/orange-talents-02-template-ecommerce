package br.com.orangetalents.mercadolivre.produto;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Autowired
	private UploaderFake uploaderFake;
	
	@InitBinder(value = "novoProdutoRequest")
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
	
	@PostMapping("/produtos/{id}/imagens")
	@Transactional
	public String postImagens (@PathVariable("id") Long id, @Valid NovasImagensRequest request) {
		
		/*
		 *1) enviar imagens para o local onde elas vão ficar
		 *2) pegar o link de todas as imagens
		 *3) associar esses links com o produto em questão
		 *4) preciso carregar o produto
		 *5) depois que associar eu preciso atualizar a nova versão do produto 
		 */
		
		
		Set<String> links = uploaderFake.envia(request.getImagens());
		Produto produto = manager.find(Produto.class, id);
		produto.associaImagens(links);
		
		manager.merge(produto);
		
		return produto.toString();
	}
	
}
