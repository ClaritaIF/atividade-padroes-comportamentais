package br.edu.ifpb.ads.padroes.atv2;

import java.math.BigDecimal;

public class ProdutoFisico implements ItemTributavel {

    private final String nome;
    private final BigDecimal valor;

    public ProdutoFisico(String nome, BigDecimal valor) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do produto deve ser informado.");
        }
        if (valor == null || valor.signum() < 0) {
            throw new IllegalArgumentException("Valor do produto deve ser zero ou positivo.");
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
