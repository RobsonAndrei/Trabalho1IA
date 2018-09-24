package Negocio;

import java.util.Random;

import javax.sound.midi.Soundbank;

public class Tabuleiro {

	private String[][] matriz;

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
		Random r = new Random();
		int result = r.nextInt(4);

		switch (result) {
		case 0:
			for (int i = 0; i < matriz.length; i++) {
				matriz[i][0] = "M";
			}
			Random r2 = new Random();
			matriz[r2.nextInt(matriz.length)][0] = "D";
			break;

		case 1:
			for (int i = 0; i < matriz.length; i++) {
				matriz[0][i] = "M";
			}
			Random r3 = new Random();
			matriz[0][r3.nextInt(matriz.length)] = "D";
			break;

		case 2:
			for (int i = 0; i < matriz.length; i++) {
				matriz[matriz.length - 1][i] = "M";
			}
			Random r4 = new Random();
			matriz[matriz.length - 1][r4.nextInt(matriz.length)] = "D";
			break;

		case 3:
			for (int i = 0; i < matriz.length; i++) {
				matriz[i][matriz.length - 1] = "M";
			}
			Random r5 = new Random();
			matriz[r5.nextInt(matriz.length)][matriz.length - 1] = "D";
			break;

		default:
			break;
		}
	}

	public void insereAgente() {

		Random x = new Random();
		Random y = new Random();
		int xis = x.nextInt(matriz.length);
		int ips = y.nextInt(matriz.length);

		while (!matriz[xis][ips].equals("")) {
			xis = x.nextInt(matriz.length);
			ips = y.nextInt(matriz.length);
			break;
		}

		if (matriz[xis][ips].equals("D")) {
			matriz[x.nextInt(matriz.length)][y.nextInt(matriz.length)] = "A";
		}
		if (matriz[xis][ips].equals("M")) {
			matriz[x.nextInt(matriz.length)][y.nextInt(matriz.length)] = "A";
		}
		matriz[xis][ips] = "A";

	}

	public String posicaoAgente() {

		String msn = "";

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[j][i].equals("A")) {
					msn = msn + i + " " + j;
				}
			}
		}

		return msn;

	}

	public int posiCaoXAgente() {
		int result = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j].equals("A")) {
					result = i;
				}
			}
		}

		return result;
	}

	public int posiCaoYAgente() {
		int result = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j].equals("A")) {
					result = j;
				}
			}
		}

		return result;
	}

	public String posicaoPorta() {
		String msn = "";

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j].equals("D")) {
					msn = msn + i + " " + j;
				}
			}
		}

		return msn;

	}

	public void inserirBuracos(int num) {

		Random r1 = new Random();
		Random r2 = new Random();

		for (int i = 0; i < num; i++) {
			int x = r1.nextInt(matriz.length);
			int y = r2.nextInt(matriz.length);

			if (matriz[x][y].equals("") && !matriz[x][y].equals("B")) {
				matriz[x][y] = "B";
			} else {
				x = r1.nextInt(matriz.length);
				y = r2.nextInt(matriz.length);
				if (matriz[x][y].equals("")) {
					matriz[x][y] = "B";
				}

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
			vetorVisinhos[0] = matriz[posX][posY-1];
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
