
import javax.sound.sampled.*;

public class SomMorse {

    private static final int FREQUENCIA = 700;
    private static final int DURACAO_PONTO = 150;
    private static final int DURACAO_TRACO = 450;
    private static final int PAUSA_SINAL = 150;

    private void emitirSom(int duracao) {

        float taxaAmostragem = 44100;

        AudioFormat formato = new AudioFormat(
                taxaAmostragem, 16, 1, true, false
        );

        try (SourceDataLine linha = AudioSystem.getSourceDataLine(formato)) {

            linha.open(formato);
            linha.start();

            int totalAmostras =
                    (int) (duracao * taxaAmostragem / 1000);

            byte[] audio = new byte[totalAmostras * 2];

            for (int i = 0; i < totalAmostras; i++) {

                double tempo = i / taxaAmostragem;

                double onda = Math.sin(
                        2 * Math.PI * FREQUENCIA * tempo
                );

                short amostra = (short) (onda * 12000);

                audio[i * 2] = (byte) (amostra & 0xFF);
                audio[i * 2 + 1] =
                        (byte) ((amostra >> 8) & 0xFF);
            }

            linha.write(audio, 0, audio.length);
            linha.drain();

        } catch (LineUnavailableException e) {
            System.out.println(
                    "Erro ao reproduzir som: " + e.getMessage()
            );
        }
    }

    private void pausar(int milissegundos) {

        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void reproduzir(String codigoMorse) {

        System.out.println(
                "\nReproduzindo Código Morse: " + codigoMorse
        );

        String[] palavras = codigoMorse.trim().split("\\s+");

        for (int i = 0; i < palavras.length; i++) {

            String codigo = palavras[i];

            if (codigo.equals("/")) {
                // A separação entre palavras totaliza 7 unidades
                pausar(4 * DURACAO_PONTO);
                continue;
            }

            for (int j = 0; j < codigo.length(); j++) {

                char sinal = codigo.charAt(j);

                if (sinal == '.') {
                    emitirSom(DURACAO_PONTO);

                } else if (sinal == '-') {
                    emitirSom(DURACAO_TRACO);
                }

                if (j < codigo.length() - 1) {
                    pausar(PAUSA_SINAL);
                }
            }

            if (i < palavras.length - 1) {
                pausar(3 * DURACAO_PONTO);
            }
        }

        System.out.println("Reprodução finalizada!");
    }
}
