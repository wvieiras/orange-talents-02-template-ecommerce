package br.com.orangetalents.mercadolivre.opniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.orangetalents.mercadolivre.produto.Produto;
import br.com.orangetalents.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer nota;
	private String titulo;
	private String descricao;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario dono;
	
	public Opiniao(Integer nota, String titulo, String descricao,
			Produto produto, Usuario dono) {
		
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.dono = dono;
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

	public Produto getProduto() {
		return produto;
	}

	public Usuario getDono() {
		return dono;
	}

	@Override
	public String toString() {
		return "Opiniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", produto="
				+ produto + ", dono=" + dono + "]";
	}
	
	
	
}
