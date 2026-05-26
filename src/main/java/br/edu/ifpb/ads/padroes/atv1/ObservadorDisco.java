package br.edu.ifpb.ads.padroes.atv1;

public interface ObservadorDisco {

    boolean deveSerNotificado(Disco disco);

    void atualizar(Disco disco);

}
