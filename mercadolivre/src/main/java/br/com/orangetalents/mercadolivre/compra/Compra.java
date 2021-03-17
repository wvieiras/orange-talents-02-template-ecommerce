package br.com.orangetalents.mercadolivre.compra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.orangetalents.mercadolivre.produto.Produto;
import br.com.orangetalents.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

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

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();
	
	
	@Deprecated
	public Compra() {
	}


	public Compra(Produto produtoASerComprado, int quantidade, Usuario comprador, GatewayPagamento gateway) {
	
		this.produtoEscolhido = produtoASerComprado;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.gatewayPagamento = gateway;
		
	}
	
	
	public void setTransacoes(Set<Transacao> transacoes) {
		this.transacoes = transacoes;
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
				+ produtoEscolhido + ", comprador=" + comprador + ", transacoes=" + transacoes + "]";
	}
	public Long getId() {
		return id;
	}
	public void adicionaTransacao(@Valid RetornoPagamentoRequest request) {
		Transacao novaTransacao = request.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação igual a essa processada "+novaTransacao);
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());
		
		Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Essa compra já foi concluida com sucesso");
		
		this.transacoes.add(request.toTransacao(this));
	}
	public void adicionaTransacao(@Valid RetornoPaypalRequest request) {
		Transacao novaTransacao = request.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação igual a essa processada "+novaTransacao);
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());
		
		Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Essa compra já foi concluida com sucesso");
		
		this.transacoes.add(request.toTransacao(this));
		
		//Utilizando polimorfismo -> https://www.youtube.com/watch?v=_QYVYx2ENDQ
	}
	
}
