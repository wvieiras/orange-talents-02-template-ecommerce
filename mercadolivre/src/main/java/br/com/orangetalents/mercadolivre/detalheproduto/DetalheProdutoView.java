package br.com.orangetalents.mercadolivre.detalheproduto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.IntStream;

import br.com.orangetalents.mercadolivre.produto.Produto;

public class DetalheProdutoView {
	

	private String nome;

	private String descricao;
	
	private BigDecimal preco;

	private Set<DetalheProdutoCaracteristicas> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;

	private Set<Map<String, String>> opinioes;

	private double mediaNotas;
	
	public DetalheProdutoView(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getValor();
		this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristicas::new);
		this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
		this.opinioes = produto.mapeiaOpinioes(
				opiniao -> {
					return Map.of("titulo", opiniao.getTitulo(), "descricao",opiniao.getDescricao());
				});
		Set<Integer> notas = produto.mapeiaOpinioes(opiniao -> opiniao.getNota());
		OptionalDouble possivelMedia =  notas.stream().mapToInt(nota -> nota).average();
		
		if(possivelMedia.isPresent()) {
			this.mediaNotas = possivelMedia.getAsDouble();
		}
	}
	
	

	public double getMediaNotas() {
		return mediaNotas;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public Set<DetalheProdutoCaracteristicas> getCaracteristicas() {
		return caracteristicas;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}


	public SortedSet<String> getPerguntas() {
		return perguntas;
	}


	public void setPerguntas(SortedSet<String> perguntas) {
		this.perguntas = perguntas;
	}


	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	
	
}
