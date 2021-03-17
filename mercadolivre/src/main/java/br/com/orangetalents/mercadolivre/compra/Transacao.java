package br.com.orangetalents.mercadolivre.compra;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private StatusTransacao status;
	
	@NotBlank
	private String idTransacao;
	
	@NotNull
	private LocalDateTime instante;
	
	@NotNull
	@ManyToOne
	private Compra compra;

	@Deprecated
	public Transacao() {
		
	}

	public Transacao(StatusTransacao status, @NotBlank String idTransacao, Compra compra) {
		this.status = status;
		this.idTransacao = idTransacao;
		this.instante = LocalDateTime.now();
		this.compra = compra;
	}
	
	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
	}

	@Override
	public String toString() {
		return "Transacao [status=" + status + ", idTransacao=" + idTransacao + ", instante=" + instante + "]";
	}
	
	
	
}
