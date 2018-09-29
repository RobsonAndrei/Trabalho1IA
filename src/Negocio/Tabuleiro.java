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
				matriz[i][j] = "-";
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
			this.posicaoYPorta = r;
			this.posicaoXPorta = 0;
			break;

		case 1:
			for (int i = 0; i < this.matriz.length; i++) {
				this.matriz[0][i] = "M";
			}
			this.matriz[0][r] = "D";
			this.posicaoYPorta = 0;
			this.posicaoXPorta = r;
			break;

		case 2:
			for (int i = 0; i < this.matriz.length; i++) {
				this.matriz[this.matriz.length - 1][i] = "M";
			}
			this.matriz[this.matriz.length - 1][r] = "D";
			this.posicaoYPorta = (this.matriz.length - 1);
			this.posicaoXPorta = r;
			break;

		case 3:
			for (int i = 0; i < this.matriz.length; i++) {
				this.matriz[i][this.matriz.length - 1] = "M";
			}
			this.matriz[r][this.matriz.length - 1] = "D";
			this.posicaoYPorta = r;
			this.posicaoXPorta = (this.matriz.length - 1);
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
		} while (!this.matriz[xis][ips].equals("-") && !this.matriz[xis][ips].equals("D")
				&& this.matriz[xis][ips].equals("M"));

		this.matriz[xis][ips] = "A";

		this.posicaoXAgente = ips;
		this.posicaoYAgente = xis;

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

			if (!this.matriz[x][y].equals("-growing") && !this.matriz[x][y].equals("M")
					&& !this.matriz[x][y].equals("D") && !this.matriz[x][y].equals("B")
					&& !this.matriz[x][y].equals("A") && visinhosContem(y, x, "B") == false) {
				this.matriz[x][y] = "B";
			} 
		}

	}

	public Boolean visinhosContem(int posY, int posX, String msn) {
		boolean contem = false;

		String[] vetorVisinhos = new String[1];
		int ultPos = this.matriz.length - 1;

		if (posX == 0 && posY == 0) {// Canto superior esquerdo
			vetorVisinhos = new String[3];
			vetorVisinhos[0] = matriz[posX][posY + 1];
			vetorVisinhos[1] = matriz[posX + 1][posY];
			vetorVisinhos[2] = matriz[posX + 1][posY + 1];
			for (int i = 0; i < vetorVisinhos.length; i++) {
				if (vetorVisinhos[i].equals("B")) {
					contem = true;
				}
			}

		} else {
			if (posX == ultPos && posY == ultPos) { // Canto inferior direito
				vetorVisinhos = new String[3];
				vetorVisinhos[0] = matriz[posX][posY - 1];
				vetorVisinhos[1] = matriz[posX - 1][posY - 1];
				vetorVisinhos[2] = matriz[posX - 1][posY];
				for (int i = 0; i < vetorVisinhos.length; i++) {
					if (vetorVisinhos[i].equals("B")) {
						contem = true;
					}
				}

			} else {
				if ((posX == 0) && (posY >= 1 && posY <= ultPos - 1)) { // Corredor superior
					vetorVisinhos = new String[5];
					vetorVisinhos[0] = matriz[posX][posY - 1];
					vetorVisinhos[1] = matriz[posX][posY + 1];
					vetorVisinhos[2] = matriz[posX + 1][posY - 1];
					vetorVisinhos[3] = matriz[posX + 1][posY];
					vetorVisinhos[4] = matriz[posX + 1][posY + 1];

					for (int i = 0; i < vetorVisinhos.length; i++) {
						if (vetorVisinhos[i].equals("B")) {
							contem = true;
						}
					}
				} else {

					if ((posX >= 1 && posX <= ultPos - 1) && posY == 0) { // Corredor esquerdo
						vetorVisinhos = new String[5];
						vetorVisinhos[0] = matriz[posX - 1][posY];
						vetorVisinhos[1] = matriz[posX - 1][posY + 1];
						vetorVisinhos[2] = matriz[posX][posY + 1];
						vetorVisinhos[3] = matriz[posX + 1][posY];
						vetorVisinhos[4] = matriz[posX + 1][posY + 1];

						for (int i = 0; i < vetorVisinhos.length; i++) {
							if (vetorVisinhos[i].equals("B")) {
								contem = true;
							}
						}
					} else {
						if ((posX == ultPos) && (posY >= 1 && posY <= ultPos - 1)) { // Corredor inferior
							vetorVisinhos = new String[5];
							vetorVisinhos[0] = matriz[posX][posY - 1];
							vetorVisinhos[1] = matriz[posX - 1][posY - 1];
							vetorVisinhos[2] = matriz[posX - 1][posY];
							vetorVisinhos[3] = matriz[posX - 1][posY + 1];
							vetorVisinhos[4] = matriz[posX][posY + 1];

							for (int i = 0; i < vetorVisinhos.length; i++) {
								if (vetorVisinhos[i].equals("B")) {
									contem = true;
								}
							}
						} else {
							if (posX == ultPos && posY == 0) { // Canto inferior esquerdo
								vetorVisinhos = new String[3];
								vetorVisinhos[0] = matriz[posX - 1][posY];
								vetorVisinhos[1] = matriz[posX - 1][posY + 1];
								vetorVisinhos[2] = matriz[posX][posY + 1];
								for (int i = 0; i < vetorVisinhos.length; i++) {
									if (vetorVisinhos[i].equals("B")) {
										contem = true;
									}
								}
							} else {
								if (posX == 0 && posY == ultPos) { // Canto superior direito
									vetorVisinhos = new String[3];
									vetorVisinhos[0] = matriz[posX][posY - 1];
									vetorVisinhos[1] = matriz[posX + 1][posY - 1];
									vetorVisinhos[2] = matriz[posX + 1][posY];

									for (int i = 0; i < vetorVisinhos.length; i++) {
										if (vetorVisinhos[i].equals("B")) {
											contem = true;
										}
									}
								} else {
									if ((posX >= 1 && posX <= ultPos - 1) && (posY == ultPos)) { // Corredor direito
										vetorVisinhos = new String[5];
										vetorVisinhos[0] = matriz[posX - 1][posY];
										vetorVisinhos[1] = matriz[posX - 1][posY - 1];
										vetorVisinhos[2] = matriz[posX][posY - 1];
										vetorVisinhos[3] = matriz[posX + 1][posY - 1];
										vetorVisinhos[4] = matriz[posX + 1][posY];

										for (int i = 0; i < vetorVisinhos.length; i++) {
											if (vetorVisinhos[i].equals("B")) {
												contem = true;
											}
										}
									} else {
										if ((posX >= 1 && posX <= ultPos - 1) && (posY >= 1 && posY <= ultPos - 1)) {
											vetorVisinhos = new String[8];
											vetorVisinhos[0] = matriz[posX - 1][posY - 1];
											vetorVisinhos[1] = matriz[posX - 1][posY];
											vetorVisinhos[2] = matriz[posX - 1][posY + 1];
											vetorVisinhos[3] = matriz[posX][posY - 1];
											vetorVisinhos[4] = matriz[posX][posY + 1];
											vetorVisinhos[5] = matriz[posX + 1][posY - 1];
											vetorVisinhos[6] = matriz[posX + 1][posY];
											vetorVisinhos[7] = matriz[posX + 1][posY + 1];

											for (int i = 0; i < vetorVisinhos.length; i++) {
												if (vetorVisinhos[i].equals("B")) {
													contem = true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

		}

		return contem;
	}

	/***
	 * Posiçao dos vizinhos na matriz this.matriz[x-1][y+1] this.matriz[x][y-1]
	 * this.matriz[x][y+1] this.matriz[x+1][y-1] this.matriz[x+1][y]
	 * this.matriz[x+1][y+1]
	 * 
	 * 
	 * 
	 */

	public String[] visinhos(int posY, int posX) {
		String[] vetorVisinhos = new String[1];
		int ultPos = this.matriz.length - 1;

		if (posX == 0 && posY == 0) {// Canto superior esquerdo
			vetorVisinhos = new String[3];
			vetorVisinhos[0] = matriz[posX][posY + 1];
			vetorVisinhos[1] = matriz[posX + 1][posY];
			vetorVisinhos[2] = matriz[posX + 1][posY + 1];

		} else {
			if (posX == ultPos && posY == ultPos) { // Canto inferior direito
				vetorVisinhos = new String[3];
				vetorVisinhos[0] = matriz[posX][posY - 1];
				vetorVisinhos[1] = matriz[posX - 1][posY - 1];
				vetorVisinhos[2] = matriz[posX - 1][posY];

			} else {
				if ((posX == 0) && (posY >= 1 && posY <= ultPos - 1)) { // Corredor superior
					vetorVisinhos = new String[5];
					vetorVisinhos[0] = matriz[posX][posY - 1];
					vetorVisinhos[1] = matriz[posX][posY + 1];
					vetorVisinhos[2] = matriz[posX + 1][posY - 1];
					vetorVisinhos[3] = matriz[posX + 1][posY];
					vetorVisinhos[4] = matriz[posX + 1][posY + 1];
				} else {

					if ((posX >= 1 && posX <= ultPos - 1) && posY == 0) { // Corredor esquerdo
						vetorVisinhos = new String[5];
						vetorVisinhos[0] = matriz[posX - 1][posY];
						vetorVisinhos[1] = matriz[posX - 1][posY + 1];
						vetorVisinhos[2] = matriz[posX][posY + 1];
						vetorVisinhos[3] = matriz[posX + 1][posY];
						vetorVisinhos[4] = matriz[posX + 1][posY + 1];
					} else {
						if ((posX == ultPos) && (posY >= 1 && posY <= ultPos - 1)) { // Corredor inferior
							vetorVisinhos = new String[5];
							vetorVisinhos[0] = matriz[posX][posY - 1];
							vetorVisinhos[1] = matriz[posX - 1][posY - 1];
							vetorVisinhos[2] = matriz[posX - 1][posY];
							vetorVisinhos[3] = matriz[posX - 1][posY + 1];
							vetorVisinhos[4] = matriz[posX][posY + 1];
						} else {
							if (posX == ultPos && posY == 0) { // Canto inferior esquerdo
								vetorVisinhos = new String[3];
								vetorVisinhos[0] = matriz[posX - 1][posY];
								vetorVisinhos[1] = matriz[posX - 1][posY + 1];
								vetorVisinhos[2] = matriz[posX][posY + 1];
							} else {
								if (posX == 0 && posY == ultPos) { // Canto superior direito
									vetorVisinhos = new String[3];
									vetorVisinhos[0] = matriz[posX][posY - 1];
									vetorVisinhos[1] = matriz[posX + 1][posY - 1];
									vetorVisinhos[2] = matriz[posX + 1][posY];
								} else {
									if ((posX >= 1 && posX <= ultPos - 1) && (posY == ultPos)) { // Corredor direito
										vetorVisinhos = new String[5];
										vetorVisinhos[0] = matriz[posX - 1][posY];
										vetorVisinhos[1] = matriz[posX - 1][posY - 1];
										vetorVisinhos[2] = matriz[posX][posY - 1];
										vetorVisinhos[3] = matriz[posX + 1][posY - 1];
										vetorVisinhos[4] = matriz[posX + 1][posY];
									} else {
										if ((posX >= 1 && posX <= ultPos - 1) && (posY >= 1 && posY <= ultPos - 1)) {
											vetorVisinhos = new String[8];
											vetorVisinhos[0] = matriz[posX - 1][posY - 1];
											vetorVisinhos[1] = matriz[posX - 1][posY];
											vetorVisinhos[2] = matriz[posX - 1][posY + 1];
											vetorVisinhos[3] = matriz[posX][posY - 1];
											vetorVisinhos[4] = matriz[posX][posY + 1];
											vetorVisinhos[5] = matriz[posX + 1][posY - 1];
											vetorVisinhos[6] = matriz[posX + 1][posY];
											vetorVisinhos[7] = matriz[posX + 1][posY + 1];
										}
									}
								}
							}
						}
					}
				}
			}

		}

		return vetorVisinhos;
	}

	public static void main(String[] args) {
		Tabuleiro tab = new Tabuleiro(10);

		tab.randomizaMuroePorta();
		tab.insereAgente();

		tab.inserirBuracos(28);

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
