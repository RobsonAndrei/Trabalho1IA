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
				if (matriz[i][j].equals("A")) {
					msn = msn + i + " " + j;
				}
			}
		}

		return msn;

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
	
	

	public static void main(String[] args) {
		Tabuleiro tab = new Tabuleiro(10);

		tab.randomizaMuroePorta();
		tab.insereAgente();

		tab.inserirBuracos(5);

		tab.mostrarTabuleiro();
		System.out.println("Posicao do Agente\t" + tab.posicaoAgente());
		System.out.println("Posicao da porta\t" + tab.posicaoPorta());

	}

}
