package br.com.orangetalents.mercadolivre.opniao;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.orangetalents.mercadolivre.produto.Produto;
import br.com.orangetalents.mercadolivre.usuario.Usuario;

public class NovaOpiniaoRequest {
	
	@Min(1)
	@Max(5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Length(max=500)
	private String descricao;
	

	public NovaOpiniaoRequest(@Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Opiniao toModel(Produto produto, Usuario dono) {
		
		return new Opiniao(nota, titulo, descricao, produto, dono);
	}
	
	
}
