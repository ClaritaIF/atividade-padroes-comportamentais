package br.edu.ifpb.ads.padroes.atv2;

import java.util.List;

public class ProcessadorItens {

    public void processar(List<? extends ItemTributavel> itens, ItemVisitor visitor) {
        itens.forEach(item -> item.aceitar(visitor));
    }

}
