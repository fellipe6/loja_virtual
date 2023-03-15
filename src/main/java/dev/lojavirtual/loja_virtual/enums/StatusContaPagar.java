package dev.lojavirtual.loja_virtual.enums;


public enum StatusContaPagar {

    COBRANCA("Pagar"),
    VENCIDA("Vencida"),
    ABERTA("Aberta"),
    NEGOCIADA("Negociada"),
    QUITADA("Quitada");

    private String descricao;

    private StatusContaPagar(String descricao){
    this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    public String toString(){
        return this.descricao;
    }
}
