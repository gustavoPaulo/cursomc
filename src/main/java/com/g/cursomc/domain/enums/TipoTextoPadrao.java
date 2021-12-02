package com.g.cursomc.domain.enums;

public enum TipoTextoPadrao {

    ERRO_VALIDACAO("Erro de validação."),
    ERRO_DATA_INTEGRITY_CATEGORIA("Não é possível excluir uma categoria que possui produtos vinculados."),
    ERRO_DATA_INTEGRITY_CLIENTE("Não é possível excluir um cliente que possui pedidos vinculados."),

    CPF_INVALIDO("CPF inválido."),
    CNPJ_INVALIDO("CNPJ inválido."),

    EMAIL_JA_EXISTENTE("E-mail já atribuído a outro cliente."),
    PEDIDO_CONFIMADO("Pedido confirmado!"),
    PATH_CORPO_EMAIL("email/confirmacaoPedido"),

    PADRAO_DATA_HORA("dd/MM/yyyy HH:mm:ss");

    private String descricao;

    TipoTextoPadrao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
