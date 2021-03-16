package br.com.orangetalents.mercadolivre.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {
	
	private GatewayPagamento gateway;
	
	@Positive
	private Integer quantidade;
	
	@NotNull
	private Long idProduto;

	public Integer getQuantidade() {
		return quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
	
	
	
	
}
