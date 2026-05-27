package br.edu.ifpb.ads.padroes.atv2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelatorioImpostosVisitor implements ItemVisitor {

    private static final BigDecimal IMPOSTO_PRODUTO_FISICO = new BigDecimal("0.10");
    private static final BigDecimal IMPOSTO_SERVICO = new BigDecimal("0.15");

    private final List<String> linhas = new ArrayList<>();
    private BigDecimal totalImpostos = BigDecimal.ZERO;

    @Override
    public void visitar(ProdutoFisico produtoFisico) {
        adicionarLinha("Produto Fisico", produtoFisico, IMPOSTO_PRODUTO_FISICO);
    }

    @Override
    public void visitar(Servico servico) {
        adicionarLinha("Servico", servico, IMPOSTO_SERVICO);
    }

    public List<String> getLinhas() {
        return Collections.unmodifiableList(linhas);
    }

    public BigDecimal getTotalImpostos() {
        return totalImpostos;
    }

    public String gerarRelatorio() {
        return String.join(System.lineSeparator(), linhas)
                + System.lineSeparator()
                + "Total de impostos: R$ " + formatarMoeda(totalImpostos);
    }

    private void adicionarLinha(String tipo, ItemTributavel item, BigDecimal aliquota) {
        BigDecimal imposto = calcularImposto(item, aliquota);
        totalImpostos = totalImpostos.add(imposto);

        linhas.add(tipo
                + " - " + item.getNome()
                + " | Valor: R$ " + formatarMoeda(item.getValor())
                + " | Imposto: R$ " + formatarMoeda(imposto));
    }

    private BigDecimal calcularImposto(ItemTributavel item, BigDecimal aliquota) {
        return item.getValor()
                .multiply(aliquota)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private String formatarMoeda(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

}
