package br.edu.ifpb.ads.padroes.atv1;

public class PushNotificacao implements CanalNotificacao {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando PUSH NOTIFICATION: " + mensagem);
    }

}
