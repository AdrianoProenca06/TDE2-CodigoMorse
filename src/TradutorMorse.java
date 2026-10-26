import java.util.HashMap;
import java.util.Map;

public class TradutorMorse {

    private final ArvoreMorse arvore;
    private final Map<Character, String> tabelaMorse;

    public TradutorMorse() {
        arvore = new ArvoreMorse();
        tabelaMorse = new HashMap<>();

        carregarAlfabeto();
    }

    private void carregarAlfabeto() {

        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        String[] codigos = {
                ".-", "-...", "-.-.", "-..", ".", "..-.",
                "--.", "....", "..", ".---", "-.-", ".-..",
                "--", "-.", "---", ".--.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-",
                "-.--", "--..",
                "-----", ".----", "..---", "...--", "....-",
                ".....", "-....", "--...", "---..", "----."
        };

        for (int i = 0; i < letras.length(); i++) {

            char letra = letras.charAt(i);
            String codigo = codigos[i];

            tabelaMorse.put(letra, codigo);
            arvore.inserir(letra, codigo);
        }
    }

    public String codificar(String texto) {

        StringBuilder resultado = new StringBuilder();
        texto = texto.toUpperCase();

        for (char letra : texto.toCharArray()) {

            if (letra == ' ') {
                resultado.append("/ ");

            } else if (tabelaMorse.containsKey(letra)) {
                resultado.append(tabelaMorse.get(letra));
                resultado.append(" ");

            } else {
                resultado.append("? ");
            }
        }

        return resultado.toString().trim();
    }

    public String decodificar(String codigoMorse) {

        StringBuilder resultado = new StringBuilder();

        String[] codigos = codigoMorse.trim().split("\\s+");

        for (String codigo : codigos) {

            if (codigo.equals("/")) {
                resultado.append(" ");

            } else {
                resultado.append(arvore.decodificar(codigo));
            }
        }

        return resultado.toString();
    }

    public void mostrarArvore() {
        arvore.imprimirArvore();
    }
}
