package br.edu.ifpb.ads.padroes.atv2;

import java.math.BigDecimal;

public interface ItemTributavel {

    String getNome();

    BigDecimal getValor();

    void aceitar(ItemVisitor visitor);

}
