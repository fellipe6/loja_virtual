package br.com.nazasoft.lojavirtual.entity.enums;

public enum StatusContaPagar {

	COBRANCA("pagar"),
	VENCIDA("vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada"),
	ALUGUEL("Aluguel"),
	FUNCIONARIO("Funcionario"),
	NEGOCIADA("Renegociada");
	
	private String descricao;

	private StatusContaPagar(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	@Override
	public String toString() {
		return this.getDescricao();
	}
	
}
