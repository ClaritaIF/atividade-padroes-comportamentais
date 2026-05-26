package br.edu.ifpb.ads.padroes.atv1;

public class InteresseDisco implements ObservadorDisco {

    private final TipoInteresse tipo;
    private final String valor;
    private final CanalNotificacao canalNotificacao;

    public InteresseDisco(TipoInteresse tipo, String valor, CanalNotificacao canalNotificacao) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de interesse deve ser informado.");
        }
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Valor de interesse deve ser informado.");
        }
        if (canalNotificacao == null) {
            throw new IllegalArgumentException("Canal de notificacao deve ser informado.");
        }

        this.tipo = tipo;
        this.valor = valor;
        this.canalNotificacao = canalNotificacao;
    }

    @Override
    public boolean deveSerNotificado(Disco disco) {
        return tipo.corresponde(disco, valor);
    }

    @Override
    public void atualizar(Disco disco) {
        canalNotificacao.enviar(tipo.mensagem(disco));
    }

}
