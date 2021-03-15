package br.com.orangetalents.mercadolivre.detalheproduto;

import br.com.orangetalents.mercadolivre.produto.CaracteristicaProduto;

public class DetalheProdutoCaracteristicas {
	
	private String nome;
	
	private String descricao;

	public DetalheProdutoCaracteristicas(CaracteristicaProduto caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
	
}
