package br.com.orangetalents.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/categoria")
	@Transactional
	public String post (@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toModel(manager);
		manager.persist(categoria);
		return categoria.toString();
		
	}
}
