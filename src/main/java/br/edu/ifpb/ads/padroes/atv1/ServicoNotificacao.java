package br.edu.ifpb.ads.padroes.atv1;

/**
 * Classe responsavel por enviar notificacoes.
 */
public class ServicoNotificacao {

    public void enviarNotificacao(CanalNotificacao canal, String mensagem) {
        canal.enviar(mensagem);
    }

    public void enviarNotificacao(String canal, String mensagem) {
        enviarNotificacao(CanalNotificacaoFactory.criar(canal), mensagem);
    }

}
