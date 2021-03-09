package br.com.orangetalents.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;

import br.com.orangetalents.mercadolivre.categoria.Categoria;
import br.com.orangetalents.mercadolivre.compartilhado.ExistsId;
import br.com.orangetalents.mercadolivre.usuario.Usuario;

public class NovoProdutoRequest {
	
	//Atributos
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	@NotBlank
	@Length(max=1000)
	private String descricao;
	
	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id") //Está linha faz com que o valor exista no BD
	@ManyToOne
	private Long idCategoria;
	
	
	private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();
	
	
	//Construtor
	public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @Positive Integer quantidade, @NotBlank @Length(max = 1000) String descricao,
			@NotNull Long idCategoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}
	
	
	@Override
	public String toString() {
		return "NovoProdutoRequest [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", descricao="
				+ descricao + ", idCategoria=" + idCategoria + "]";
	}


	public Produto toModel(EntityManager manager, Usuario dono) {
			Categoria categoria = manager.find(Categoria.class, idCategoria);
		return new Produto(nome,quantidade,descricao,valor,categoria,dono);
	}
		
	
}
