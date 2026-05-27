package br.edu.ifpb.ads.padroes.atv2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImpressaoValoresVisitor implements ItemVisitor {

    private final List<String> linhas = new ArrayList<>();

    @Override
    public void visitar(ProdutoFisico produtoFisico) {
        adicionarLinha("Produto Fisico", produtoFisico);
    }

    @Override
    public void visitar(Servico servico) {
        adicionarLinha("Servico", servico);
    }

    public List<String> getLinhas() {
        return Collections.unmodifiableList(linhas);
    }

    public String imprimir() {
        return String.join(System.lineSeparator(), linhas);
    }

    private void adicionarLinha(String tipo, ItemTributavel item) {
        linhas.add(tipo
                + " - " + item.getNome()
                + " | Valor: R$ " + formatarMoeda(item.getValor()));
    }

    private String formatarMoeda(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

}
