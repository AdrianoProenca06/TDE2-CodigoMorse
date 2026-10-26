public class ArvoreMorse {

    private NoMorse raiz;

    public ArvoreMorse() {
        raiz = new NoMorse(' ');
    }

    public void inserir(char caractere, String codigo) {
        NoMorse atual = raiz;

        for (char sinal : codigo.toCharArray()) {

            if (sinal == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new NoMorse(' ');
                }
                atual = atual.esquerda;

            } else if (sinal == '-') {
                if (atual.direita == null) {
                    atual.direita = new NoMorse(' ');
                }
                atual = atual.direita;

            } else {
                throw new IllegalArgumentException(
                        "Sinal Morse inválido: " + sinal
                );
            }
        }

        atual.caractere = caractere;
    }

    public char decodificar(String codigo) {
        NoMorse atual = raiz;

        for (char sinal : codigo.toCharArray()) {

            if (sinal == '.') {
                atual = atual.esquerda;

            } else if (sinal == '-') {
                atual = atual.direita;

            } else {
                return '?';
            }

            if (atual == null) {
                return '?';
            }
        }

        return atual.caractere == ' '
                ? '?'
                : atual.caractere;
    }

    public void imprimirArvore() {
        System.out.println("\nÁRVORE DE CÓDIGO MORSE");
        imprimirRecursivo(raiz, "", "RAIZ");
    }

    private void imprimirRecursivo(
            NoMorse no, String espaco, String lado) {

        if (no == null) {
            return;
        }

        String letra = no.caractere == ' '
                ? "*"
                : String.valueOf(no.caractere);

        System.out.println(
                espaco + lado + " -> " + letra
        );

        imprimirRecursivo(
                no.esquerda, espaco + "    ", "."
        );

        imprimirRecursivo(
                no.direita, espaco + "    ", "-"
        );
    }
}
