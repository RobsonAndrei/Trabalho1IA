package Negocio;

import java.util.Random;

import javax.sound.midi.Soundbank;

public class Tabuleiro {

	private String[][] matriz;
	private int posicaoXAgente = 0;
	private int posicaoYAgente = 0;
	private int posicaoXPorta = 0;
	private int posicaoYPorta = 0;
	private Random random = new Random();

	public Tabuleiro(int tamanho) {
		this.matriz = new String[tamanho][tamanho];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = "";
			}
		}
	}

	public void mostrarTabuleiro() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[j][i] + "\t");
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
		} while (!this.matriz[xis][ips].equals("") && !this.matriz[xis][ips].equals("D")
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

	public String[] visinhos(int posX, int posY) {
		String[] vetorVisinhos;
		int ultPos = matriz.length - 1;

		if (posX == 0 && posY == 0) {
			vetorVisinhos = new String[3];
			vetorVisinhos[0] = matriz[posX + 1][0];
			vetorVisinhos[1] = matriz[posX + 1][posY + 1];
			vetorVisinhos[2] = matriz[0][posY + 1];
		}

		if (posX == ultPos && posY == ultPos) {
			vetorVisinhos = new String[3];
			vetorVisinhos[0] = matriz[ultPos - 1][ultPos];
			vetorVisinhos[1] = matriz[ultPos - 1][ultPos - 1];
			vetorVisinhos[2] = matriz[ultPos][ultPos - 1];
		}

		if ((posX > 0 || posX < ultPos) && posY == 0) {

			vetorVisinhos = new String[5];
			vetorVisinhos[0] = matriz[posX - 1][posY];
			vetorVisinhos[1] = matriz[posX - 1][posY + 1];
			vetorVisinhos[2] = matriz[posX + 1][posY + 1];
			vetorVisinhos[3] = matriz[posX + 1][posY + 1];
			vetorVisinhos[4] = matriz[posX + 1][posY];

		}

		if (posX == 0 && (posY > 0 || posY < ultPos)) {

			vetorVisinhos = new String[5];
			vetorVisinhos[0] = matriz[posX][posY - 1];
			vetorVisinhos[1] = matriz[posX + 1][posY - 1];
			vetorVisinhos[2] = matriz[posX + 1][posY];
			vetorVisinhos[3] = matriz[posX + 1][posY + 1];
			vetorVisinhos[4] = matriz[posX][posY + 1];

		}

		if (posX == ultPos && (posY > 0 || posY < ultPos)) {

			vetorVisinhos = new String[5];
			vetorVisinhos[0] = matriz[posX][posY - 1];
			vetorVisinhos[1] = matriz[posX - 1][posY - 1];
			vetorVisinhos[2] = matriz[posX - 1][posY];
			vetorVisinhos[3] = matriz[posX - 1][posY + 1];
			vetorVisinhos[4] = matriz[posX][posY + 1];

		}

		if ((posX > 0 || posX < ultPos) && posY == ultPos) {

			vetorVisinhos = new String[5];
			vetorVisinhos[0] = matriz[posX - 1][posY];
			vetorVisinhos[1] = matriz[posX - 1][posY - 1];
			vetorVisinhos[2] = matriz[posX][posY - 1];
			vetorVisinhos[3] = matriz[posX + 1][posY - 1];
			vetorVisinhos[4] = matriz[posX + 1][posY - 1];

		}
		if ((posX > 0 || posX < ultPos) && (posY > 0 || posY < ultPos) && (posX != 0) && (posY != 0) && (posX != ultPos)
				&& (posY != ultPos)) {
			vetorVisinhos = new String[8];
			vetorVisinhos[0] = matriz[posX - 1][posY - 1];
			vetorVisinhos[1] = matriz[posX - 1][posY];
			vetorVisinhos[2] = matriz[posX - 1][posY + 1];
			vetorVisinhos[3] = matriz[posX][posY - 1];
			vetorVisinhos[4] = matriz[posX][posY + 1];
			vetorVisinhos[5] = matriz[posX + 1][posY - 1];
			vetorVisinhos[6] = matriz[posX + 1][posY];
			vetorVisinhos[7] = matriz[posX + 1][posY + 1];

		} else {
			vetorVisinhos = new String[1];
		}

		return vetorVisinhos;
	}

	public static void main(String[] args) {
		Tabuleiro tab = new Tabuleiro(10);

		tab.randomizaMuroePorta();
		tab.insereAgente();

		tab.inserirBuracos(5);

		tab.mostrarTabuleiro();
		String[] vis = tab.visinhos(tab.posiCaoXAgente(), tab.posiCaoYAgente());
		System.out.println("Visinhos do Agente");
		for (int i = 0; i < vis.length; i++) {
			System.out.print(vis[i] + " ");
		}
		System.out.println("\n");

		System.out.println("Posicao do Agente\t" + tab.posicaoAgente());
		System.out.println("Posicao da porta\t" + tab.posicaoPorta());

	}

}
