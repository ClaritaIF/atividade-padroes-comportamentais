package br.edu.ifpb.ads.padroes.atv1;

public final class CanalNotificacaoFactory {

    private CanalNotificacaoFactory() {
    }

    public static CanalNotificacao criar(String canal) {
        if (canal == null || canal.isBlank()) {
            throw new IllegalArgumentException("Canal de notificacao deve ser informado.");
        }

        String canalNormalizado = canal.trim().toLowerCase();

        switch (canalNormalizado) {
            case "email":
                return new EmailNotificacao();
            case "sms":
                return new SmsNotificacao();
            case "push":
            case "push notification":
            case "push-notification":
                return new PushNotificacao();
            default:
                throw new IllegalArgumentException("Canal de notificacao desconhecido: " + canal);
        }
    }

}
