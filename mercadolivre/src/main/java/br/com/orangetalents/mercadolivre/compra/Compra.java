package br.com.orangetalents.mercadolivre.compra;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.orangetalents.mercadolivre.produto.Produto;
import br.com.orangetalents.mercadolivre.usuario.Usuario;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated
	@NotNull
	private GatewayPagamento gatewayPagamento;
	
	@Positive
	private int quantidade;
	
	@ManyToOne
	@NotNull
	@Valid
	private Produto produtoEscolhido;
	
	@ManyToOne
	@NotNull
	@Valid
	private Usuario comprador;
	
	public Compra(Produto produtoASerComprado, int quantidade, Usuario comprador, GatewayPagamento gateway) {
	
		this.produtoEscolhido = produtoASerComprado;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.gatewayPagamento = gateway;
		
	}
	public int getQuantidade() {
		return quantidade;
	}


	public Usuario getComprador() {
		return comprador;
	}
	public Produto getProdutoEscolhido() {
		return produtoEscolhido;
	}
	
	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}
	
	@Override
	public String toString() {
		return "Compra [gatewayPagamento=" + gatewayPagamento + ", quantidade=" + quantidade + ", produtoEscolhido="
				+ produtoEscolhido + ", comprador=" + comprador + "]";
	}
	public Long getId() {
		return id;
	}
	
		

}
