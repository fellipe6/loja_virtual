package dev.lojavirtual.loja_virtual.enums;


public enum StatusContaReceber {

    COBRANCA("Pagar"),
    VENCIDA("Vencida"),
    ABERTA("Aberta"),
    NEGOCIADA("Negociada"),
    QUITADA("Quitada");

    private String descricao;

    private StatusContaReceber(String descricao){
    this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    public String toString(){
        return this.descricao;
    }
}
