package Negocio;

public class Main {

    public static void main(String[] args) {
        Tabuleiro tab = new Tabuleiro(10);

        tab.randomizaMuroePorta();
        tab.insereAgente();
        tab.inserirBuracos(5);

        tab.mostrarTabuleiro();
        String[] vis = tab.vizinhos(tab.posiCaoXAgente(), tab.posiCaoYAgente());
        System.out.println("Visinhos do Agente");
        for (int i = 0; i < vis.length; i++) {
            System.out.print(vis[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Posicao do Agente\t" + tab.posicaoAgente());
        System.out.println("Posicao da porta\t" + tab.posicaoPorta());

    }
}
