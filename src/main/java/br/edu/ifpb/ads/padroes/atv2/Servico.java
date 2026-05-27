package br.edu.ifpb.ads.padroes.atv2;

import java.math.BigDecimal;

public class Servico implements ItemTributavel {

    private final String nome;
    private final BigDecimal valor;

    public Servico(String nome, BigDecimal valor) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do servico deve ser informado.");
        }
        if (valor == null || valor.signum() < 0) {
            throw new IllegalArgumentException("Valor do servico deve ser zero ou positivo.");
        }

        this.nome = nome;
        this.valor = valor;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public void aceitar(ItemVisitor visitor) {
        visitor.visitar(this);
    }

}
