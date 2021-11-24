package com.g.cursomc.domain.enums;

public enum TipoTextoPadrao {

    ERRO_VALIDACAO("Erro de validação."),
    ERRO_DATA_INTEGRITY_CATEGORIA("Não é possível excluir uma categoria que possui produtos vinculados."),

    PADRAO_DATA_HORA("dd/MM/yyyy HH:mm:ss");

    private String descricao;

    TipoTextoPadrao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
