package br.edu.ifpb.ads.padroes.atv1;

public final class CanalNotificacaoFactory {

    private CanalNotificacaoFactory() {
    }

    public static CanalNotificacao criar(String canal) {
        if (canal == null || canal.isBlank()) {
            throw new IllegalArgumentException("Canal de notificacao deve ser informado.");
        }

        return switch (canal.trim().toLowerCase()) {
            case "email" -> new EmailNotificacao();
            case "sms" -> new SmsNotificacao();
            case "push", "push notification", "push-notification" -> new PushNotificacao();
            default -> throw new IllegalArgumentException("Canal de notificacao desconhecido: " + canal);
        };
    }

}
