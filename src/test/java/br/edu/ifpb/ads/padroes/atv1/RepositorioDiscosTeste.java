package br.edu.ifpb.ads.padroes.atv1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class RepositorioDiscosTeste {

    public static void main(String[] args) {
        deveNotificarTodosOsInteressadosComCanaisDiferentes();
        naoDeveNotificarQuandoNaoHouverInteresseCorrespondente();
        deveUsarCanalPadraoNosMetodosLegados();
        deveRejeitarCanalDesconhecido();

        System.out.println("Todos os testes passaram.");
    }

    private static void deveNotificarTodosOsInteressadosComCanaisDiferentes() {
        RepositorioDiscos repositorio = new RepositorioDiscos();
        repositorio.addNotificacaoDisco("blue", "email");
        repositorio.addNotificacaoArtista("miles", "sms");
        repositorio.addNotificacaoGenero("jazz", "push");
        repositorio.addNotificacaoGenero("jazz", "email");

        String saida = capturarSaida(() ->
                repositorio.addDisco(new Disco("Miles Davis", "Kind of Blue", "Jazz", 1959)));

        verificarContem(saida, "Enviando EMAIL: Novo disco adicionado: Kind of Blue");
        verificarContem(saida, "Enviando SMS: Novo disco do artista: Miles Davis");
        verificarContem(saida, "Enviando PUSH NOTIFICATION: Novo disco do genero: Jazz");
        verificarContem(saida, "Enviando EMAIL: Novo disco do genero: Jazz");
        verificarIgual(4, contarLinhas(saida), "Deveria notificar exatamente quatro interessados.");
    }

    private static void naoDeveNotificarQuandoNaoHouverInteresseCorrespondente() {
        RepositorioDiscos repositorio = new RepositorioDiscos();
        repositorio.addNotificacaoDisco("blue", "email");

        String saida = capturarSaida(() ->
                repositorio.addDisco(new Disco("John Coltrane", "A Love Supreme", "Jazz", 1965)));

        verificarVerdadeiro(saida.isBlank(), "Nao deveria notificar quando o disco nao corresponde ao interesse.");
    }

    private static void deveUsarCanalPadraoNosMetodosLegados() {
        RepositorioDiscos repositorio = new RepositorioDiscos();
        repositorio.setCanalNotificacao("sms");
        repositorio.addNotificacaoDisco("blue");

        String saida = capturarSaida(() ->
                repositorio.addDisco(new Disco("Miles Davis", "Kind of Blue", "Jazz", 1959)));

        verificarContem(saida, "Enviando SMS: Novo disco adicionado: Kind of Blue");
    }

    private static void deveRejeitarCanalDesconhecido() {
        verificarLancaExcecao(
                () -> CanalNotificacaoFactory.criar("fax"),
                IllegalArgumentException.class,
                "Canal desconhecido deveria lancar IllegalArgumentException.");
    }

    private static String capturarSaida(Runnable acao) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try (PrintStream captura = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(captura);
            acao.run();
        } finally {
            System.setOut(original);
        }

        return buffer.toString(StandardCharsets.UTF_8);
    }

    private static int contarLinhas(String texto) {
        return (int) texto.lines()
                .filter(linha -> !linha.isBlank())
                .count();
    }

    private static void verificarContem(String texto, String trechoEsperado) {
        verificarVerdadeiro(
                texto.contains(trechoEsperado),
                "Saida esperada nao encontrada: " + trechoEsperado + System.lineSeparator() + texto);
    }

    private static void verificarIgual(int esperado, int obtido, String mensagem) {
        verificarVerdadeiro(esperado == obtido, mensagem + " Esperado: " + esperado + ", obtido: " + obtido);
    }

    private static void verificarVerdadeiro(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

    private static <T extends Throwable> void verificarLancaExcecao(
            Runnable acao,
            Class<T> tipoEsperado,
            String mensagem) {
        try {
            acao.run();
        } catch (Throwable erro) {
            if (tipoEsperado.isInstance(erro)) {
                return;
            }
            throw new AssertionError("Tipo de excecao inesperado: " + erro.getClass().getName(), erro);
        }

        throw new AssertionError(mensagem);
    }

}
