package br.edu.ifpb.ads.padroes.atv1;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe responsavel por gerenciar o repositorio de discos.
 * Ela permite buscar discos por titulo, artista, genero e ano de lancamento.
 * Alem disso, permite adicionar e remover discos do repositorio.
 */
public class RepositorioDiscos {

    private final List<Disco> discos = new LinkedList<>();
    private final List<ObservadorDisco> interessados = new LinkedList<>();
    private String canalNotificacao;

    public List<Disco> buscarDiscos(String titulo) {
        return discos.stream().filter(d -> d.getTitulo().toLowerCase()
                .contains(titulo.toLowerCase())).toList();
    }

    public List<Disco> buscarDiscosPorArtista(String artista) {
        return discos.stream().filter(d -> d.getArtista().toLowerCase()
                .contains(artista.toLowerCase())).toList();
    }

    public List<Disco> buscarDiscosPorGenero(String genero) {
        return discos.stream().filter(d -> d.getGenero().toLowerCase()
                .contains(genero.toLowerCase())).toList();
    }

    public List<Disco> buscarDiscosPorAno(int ano) {
        return discos.stream().filter(d -> d.getAnoLancamento() == ano).toList();
    }

    public void addDisco(Disco disco) {
        discos.add(disco);
        notificar(disco);
    }

    public void removeDisco(Disco disco) {
        discos.remove(disco);
    }

    public String getCanalNotificacao() {
        return canalNotificacao;
    }

    public void setCanalNotificacao(String canalNotificacao) {
        this.canalNotificacao = canalNotificacao;
    }

    public void addInteresse(TipoInteresse tipo, String valor, CanalNotificacao canalNotificacao) {
        adicionarInteressado(new InteresseDisco(tipo, valor, canalNotificacao));
    }

    public void addInteresse(TipoInteresse tipo, String valor, String canalNotificacao) {
        addInteresse(tipo, valor, CanalNotificacaoFactory.criar(canalNotificacao));
    }

    public void adicionarInteressado(ObservadorDisco interessado) {
        interessados.add(interessado);
    }

    public void addNotificacaoDisco(String titulo) {
        addNotificacaoDisco(titulo, canalPadrao());
    }

    public void addNotificacaoDisco(String titulo, String canalNotificacao) {
        addInteresse(TipoInteresse.TITULO, titulo, canalNotificacao);
    }

    public void addNotificacaoDisco(String titulo, CanalNotificacao canalNotificacao) {
        addInteresse(TipoInteresse.TITULO, titulo, canalNotificacao);
    }

    public void addNotificacaoArtista(String artista) {
        addNotificacaoArtista(artista, canalPadrao());
    }

    public void addNotificacaoArtista(String artista, String canalNotificacao) {
        addInteresse(TipoInteresse.ARTISTA, artista, canalNotificacao);
    }

    public void addNotificacaoArtista(String artista, CanalNotificacao canalNotificacao) {
        addInteresse(TipoInteresse.ARTISTA, artista, canalNotificacao);
    }

    public void addNotificacaoGenero(String genero) {
        addNotificacaoGenero(genero, canalPadrao());
    }

    public void addNotificacaoGenero(String genero, String canalNotificacao) {
        addInteresse(TipoInteresse.GENERO, genero, canalNotificacao);
    }

    public void addNotificacaoGenero(String genero, CanalNotificacao canalNotificacao) {
        addInteresse(TipoInteresse.GENERO, genero, canalNotificacao);
    }

    private void notificar(Disco disco) {
        interessados.stream()
                .filter(interessado -> interessado.deveSerNotificado(disco))
                .forEach(interessado -> interessado.atualizar(disco));
    }

    private String canalPadrao() {
        if (canalNotificacao == null || canalNotificacao.isBlank()) {
            throw new IllegalStateException("Defina um canal padrao ou informe o canal ao cadastrar o interesse.");
        }
        return canalNotificacao;
    }

}
