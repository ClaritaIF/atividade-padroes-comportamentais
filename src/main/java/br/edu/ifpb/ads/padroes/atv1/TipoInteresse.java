package br.edu.ifpb.ads.padroes.atv1;

public enum TipoInteresse {

    TITULO {
        @Override
        public boolean corresponde(Disco disco, String valor) {
            return contem(disco.getTitulo(), valor);
        }

        @Override
        public String mensagem(Disco disco) {
            return "Novo disco adicionado: " + disco.getTitulo();
        }
    },
    ARTISTA {
        @Override
        public boolean corresponde(Disco disco, String valor) {
            return contem(disco.getArtista(), valor);
        }

        @Override
        public String mensagem(Disco disco) {
            return "Novo disco do artista: " + disco.getArtista();
        }
    },
    GENERO {
        @Override
        public boolean corresponde(Disco disco, String valor) {
            return contem(disco.getGenero(), valor);
        }

        @Override
        public String mensagem(Disco disco) {
            return "Novo disco do genero: " + disco.getGenero();
        }
    };

    public abstract boolean corresponde(Disco disco, String valor);

    public abstract String mensagem(Disco disco);

    protected static boolean contem(String texto, String trecho) {
        return texto != null
                && trecho != null
                && texto.toLowerCase().contains(trecho.toLowerCase());
    }

}
