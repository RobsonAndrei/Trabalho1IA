package Negocio;

import java.util.Random;

public class Tabuleiro {

    private String[][] matriz;
    private int posicaoXAgente = 0;
    private int posicaoYAgente = 0;
    private int posicaoXPorta = 0;
    private int posicaoYPorta = 0;
    private Random random = new Random();

    public Tabuleiro(int tamanho) {
        this.matriz = new String[tamanho][tamanho];
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz.length; j++) {
                this.matriz[i][j] = "";
            }
        }
    }

    public void mostrarTabuleiro() {
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[0].length; j++) {
                System.out.print(this.matriz[j][i] + "\t");
            }
            System.out.println("\n");
        }
    }

    public void randomizaMuroePorta() {
        int result = this.random.nextInt(4);
        int r = this.random.nextInt(this.matriz.length);

        switch (result) {
            case 0:
                for (int i = 0; i < this.matriz.length; i++) {
                    this.matriz[i][0] = "M";
                }
                this.matriz[r][0] = "D";
                this.posicaoXPorta = r;
                this.posicaoYPorta = 0;
                break;

            case 1:
                for (int i = 0; i < this.matriz.length; i++) {
                    this.matriz[0][i] = "M";
                }
                this.matriz[0][r] = "D";
                this.posicaoXPorta = 0;
                this.posicaoYPorta = r;
                break;

            case 2:
                for (int i = 0; i < this.matriz.length; i++) {
                    this.matriz[this.matriz.length - 1][i] = "M";
                }
                this.matriz[this.matriz.length - 1][r] = "D";
                this.posicaoXPorta = (this.matriz.length - 1);
                this.posicaoYPorta = r;
                break;

            case 3:
                for (int i = 0; i < this.matriz.length; i++) {
                    this.matriz[i][this.matriz.length - 1] = "M";
                }
                this.matriz[r][this.matriz.length - 1] = "D";
                this.posicaoXPorta = r;
                this.posicaoYPorta = (this.matriz.length - 1);
                break;

            default:
                break;
        }
    }

    public void insereAgente() {
        int xis = 0;
        int ips = 0;

        do {
            xis = this.random.nextInt(this.matriz.length);
            ips = this.random.nextInt(this.matriz.length);
        } while (!this.matriz[xis][ips].equals("")
                && !this.matriz[xis][ips].equals("D")
                && this.matriz[xis][ips].equals("M"));

        this.matriz[xis][ips] = "A";

        this.posicaoXAgente = xis;
        this.posicaoYAgente = ips;
    }

    public String posicaoAgente() {
        return this.posicaoXAgente + " " + this.posicaoYAgente;
    }

    public int posiCaoXAgente() {
        return this.posicaoXAgente;
    }

    public int posiCaoYAgente() {
        return this.posicaoYAgente;
    }

    public String posicaoPorta() {
        return this.posicaoXPorta + " " + this.posicaoYPorta;
    }

    public void inserirBuracos(int num) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < num; i++) {
            x = this.random.nextInt(this.matriz.length);
            y = this.random.nextInt(this.matriz.length);

            if (this.matriz[x][y].equals("")) {
                this.matriz[x][y] = "B";
            }
        }

    }

    public String[] vizinhos(int posX, int posY) {
        String[] vetorVizinhos = new String[8];
        int ultPos = this.matriz.length - 1;

        // REFATORANDO...
//        vetorVizinhos[0] = this.matriz[posX - 1][posY - 1];
//        vetorVizinhos[1] = this.matriz[posX - 1][posY];
//        vetorVizinhos[2] = this.matriz[posX - 1][posY + 1];
//        vetorVizinhos[3] = this.matriz[posX][posY - 1];
//        vetorVizinhos[4] = this.matriz[posX][posY + 1];
//        vetorVizinhos[5] = this.matriz[posX + 1][posY - 1];
//        vetorVizinhos[6] = this.matriz[posX + 1][posY];
//        vetorVizinhos[7] = this.matriz[posX + 1][posY + 1];;
        return vetorVizinhos;
    }
}
