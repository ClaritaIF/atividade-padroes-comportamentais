package br.edu.ifpb.ads.padroes.atv2;

public interface ItemVisitor {

    void visitar(ProdutoFisico produtoFisico);

    void visitar(Servico servico);

}
