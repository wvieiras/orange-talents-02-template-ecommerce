package br.com.orangetalents.mercadolivre.perguntas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.orangetalents.mercadolivre.produto.Produto;
import br.com.orangetalents.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	@ManyToOne
	private Usuario interessado;
	
	@ManyToOne
	private Produto produto;
	
	
	
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getInteressado() {
		return interessado;
	}

	public Produto getProduto() {
		return produto;
	}

	public Pergunta(String titulo, Usuario interessado, Produto produto) {
		this.titulo = titulo;
		this.interessado = interessado;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", interessado=" + interessado + ", produto=" + produto
				+ "]";
	}
	
	
	
	
}
