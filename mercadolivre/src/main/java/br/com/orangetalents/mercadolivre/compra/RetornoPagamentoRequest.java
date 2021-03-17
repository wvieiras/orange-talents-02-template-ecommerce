package br.com.orangetalents.mercadolivre.compra;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagamentoRequest {
	@NotBlank
	private String idTransacao;
	
	@Enumerated
	@NotNull
	private StatusRetornoPagseguro status;

	public RetornoPagamentoRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagseguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}

	@Override
	public String toString() {
		return "RetornoPagamentoRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public StatusRetornoPagseguro getStatus() {
		return status;
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}
	
	
}
