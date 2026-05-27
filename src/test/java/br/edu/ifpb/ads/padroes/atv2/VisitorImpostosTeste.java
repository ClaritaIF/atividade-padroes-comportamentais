package br.edu.ifpb.ads.padroes.atv2;

import java.math.BigDecimal;
import java.util.List;

public class VisitorImpostosTeste {

    public static void main(String[] args) {
        deveGerarRelatorioDeImpostos();
        deveImprimirValoresDosItens();

        System.out.println("Todos os testes da atv2 passaram.");
    }

    private static void deveGerarRelatorioDeImpostos() {
        List<ItemTributavel> itens = List.of(
                new ProdutoFisico("Notebook", new BigDecimal("2000.00")),
                new Servico("Consultoria", new BigDecimal("1000.00")));
        RelatorioImpostosVisitor visitor = new RelatorioImpostosVisitor();

        new ProcessadorItens().processar(itens, visitor);

        verificarContem(visitor.gerarRelatorio(), "Produto Fisico - Notebook | Valor: R$ 2000.00 | Imposto: R$ 200.00");
        verificarContem(visitor.gerarRelatorio(), "Servico - Consultoria | Valor: R$ 1000.00 | Imposto: R$ 150.00");
        verificarIgual(new BigDecimal("350.00"), visitor.getTotalImpostos(), "Total de impostos incorreto.");
    }

    private static void deveImprimirValoresDosItens() {
        List<ItemTributavel> itens = List.of(
                new ProdutoFisico("Mesa", new BigDecimal("500.00")),
                new Servico("Instalacao", new BigDecimal("300.00")));
        ImpressaoValoresVisitor visitor = new ImpressaoValoresVisitor();

        new ProcessadorItens().processar(itens, visitor);

        verificarIgual(2, visitor.getLinhas().size(), "Quantidade de linhas impressas incorreta.");
        verificarContem(visitor.imprimir(), "Produto Fisico - Mesa | Valor: R$ 500.00");
        verificarContem(visitor.imprimir(), "Servico - Instalacao | Valor: R$ 300.00");
        verificarFalso(visitor.imprimir().contains("Imposto"), "Visitor de impressao nao deveria imprimir impostos.");
    }

    private static void verificarContem(String texto, String trechoEsperado) {
        verificarVerdadeiro(
                texto.contains(trechoEsperado),
                "Texto esperado nao encontrado: " + trechoEsperado + System.lineSeparator() + texto);
    }

    private static void verificarIgual(BigDecimal esperado, BigDecimal obtido, String mensagem) {
        verificarVerdadeiro(
                esperado.compareTo(obtido) == 0,
                mensagem + " Esperado: " + esperado + ", obtido: " + obtido);
    }

    private static void verificarIgual(int esperado, int obtido, String mensagem) {
        verificarVerdadeiro(esperado == obtido, mensagem + " Esperado: " + esperado + ", obtido: " + obtido);
    }

    private static void verificarFalso(boolean condicao, String mensagem) {
        verificarVerdadeiro(!condicao, mensagem);
    }

    private static void verificarVerdadeiro(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

}
