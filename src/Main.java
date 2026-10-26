
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TradutorMorse tradutor = new TradutorMorse();
        SomMorse som = new SomMorse();

        int opcao = 0;

        do {
            System.out.println("\n==============================");
            System.out.println("      TRADUTOR MORSE");
            System.out.println("==============================");
            System.out.println("1 - Texto para Código Morse");
            System.out.println("2 - Código Morse para texto");
            System.out.println("3 - Reproduzir Código Morse com som");
            System.out.println("4 - Mostrar árvore binária");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(
                        scanner.nextLine().trim()
                );
            } catch (NumberFormatException e) {
                System.out.println("Digite uma opção válida!");
                continue;
            }

            switch (opcao) {

                case 1:
                    System.out.print("Digite um texto: ");
                    String texto = scanner.nextLine();

                    String codigo = tradutor.codificar(texto);

                    System.out.println("Código Morse: " + codigo);
                    break;

                case 2:
                    System.out.println(
                            "Separe letras por espaço e palavras por /"
                    );
                    System.out.print("Digite o Código Morse: ");

                    String morse = scanner.nextLine();

                    System.out.println(
                            "Texto decodificado: "
                                    + tradutor.decodificar(morse)
                    );
                    break;

                case 3:
                    System.out.print("Digite um texto: ");
                    String mensagem = scanner.nextLine();

                    String morseSom = tradutor.codificar(mensagem);

                    System.out.println("Código Morse: " + morseSom);
                    som.reproduzir(morseSom);
                    break;

                case 4:
                    tradutor.mostrarArvore();
                    break;

                case 5:
                    System.out.println("Encerrando programa...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);

        scanner.close();
    }
}
