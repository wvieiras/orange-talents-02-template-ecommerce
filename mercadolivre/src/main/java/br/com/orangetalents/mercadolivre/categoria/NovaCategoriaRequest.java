package br.com.orangetalents.mercadolivre.categoria;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

//Atributos e validadores
//Construtor
//Getters
//Método toModel

public class NovaCategoriaRequest {
	
	@NotBlank
	@Column(unique=true)
	private String nome;
	
	@Positive
	private Long idCategoriaMae;
	
	//Getters and Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}


	public Categoria toModel(EntityManager manager) {
		Categoria categoria = new Categoria(nome);
		if(idCategoriaMae != null) {
			Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
			Assert.notNull(categoriaMae, "O id da categoria mãe precisa ser válido");
			categoria.setMae(categoriaMae);
		}
		return categoria;
	}	
	

}
