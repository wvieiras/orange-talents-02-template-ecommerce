package br.com.orangetalents.mercadolivre.produto;

import java.math.BigDecimal;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.orangetalents.mercadolivre.categoria.Categoria;
import br.com.orangetalents.mercadolivre.usuario.Usuario;


public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Atributos
	private String nome;
	private Integer quantidade;
	private String descricao;
	private BigDecimal valor;
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Usuario dono;
	
	//Construtor
	public Produto(@NotBlank String nome, @NotNull @Positive Integer quantidade,
			@NotBlank @Length(max = 1000) String descricao, @NotNull @Positive BigDecimal valor, Categoria categoria,
			@NotNull Usuario dono) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.dono = dono;
	}

	
	//ToString
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + ", descricao=" + descricao
				+ ", valor=" + valor + ", categoria=" + categoria + ", dono=" + dono + "]";
	}


}
