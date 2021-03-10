package br.com.orangetalents.mercadolivre.perguntas;

import javax.validation.constraints.NotBlank;

import br.com.orangetalents.mercadolivre.produto.Produto;
import br.com.orangetalents.mercadolivre.usuario.Usuario;

public class NovaPerguntaRequest {
	
	@NotBlank
	private String titulo;
	
	@Deprecated
	public NovaPerguntaRequest() {
	}

	public NovaPerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "NovaPerguntaRequest [titulo=" + titulo + "]";
	}
	
	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Pergunta toModel(Usuario interessado, Produto produto) {
		
		return new Pergunta(titulo, interessado, produto);
	}
	
}
