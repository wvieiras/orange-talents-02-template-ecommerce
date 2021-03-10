package br.com.orangetalents.mercadolivre.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class CaracteristicaProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public CaracteristicaProduto() {
		
	}
	
	public CaracteristicaProduto(String nome, String descricao, Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "CaracteristicaProduto [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
