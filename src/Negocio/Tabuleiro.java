package Negocio;

import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro {

	private ArrayList<Bau> baulist;
	private Porta door;
	private ArrayList<SacoMoeda> lisSacos;
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

	public Porta getPorta() {
		return this.door;
	}

	public ArrayList<Bau> getListBaus() {
		baulist = new ArrayList<>();

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[j][i].equals("k")) {
					baulist.add(new Bau(j, i, 0));
				}
			}
		}

		return baulist;
	}

	public ArrayList<SacoMoeda> getSacos() {
		lisSacos = new ArrayList<>();

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[j][i].equals("S")) {
					lisSacos.add(new SacoMoeda(this.random.nextInt(100), j, i));
				}
			}
		}

		return lisSacos;
	}

	public void mostrarTabuleiro() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[j][i] + "\t");
			}
			System.out.println("\n");
		}
	}

	public void moVimentaAgente(String msn) {

		switch (msn) {

		case "^":
			int x = 0;
			int y = 0;
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					if (matriz[j][i].equals("A")) {
						x = i;
						y = j;
					}
				}
			}
			if (x > 0) {
				if (matriz[y][x - 1].equals("B")) {
					matriz[y][x] = "-";
					matriz[y][x - 2] = "A";
				} else {
					matriz[y][x] = "-";
					matriz[y][x - 1] = "A";

				}

			}

			mostrarTabuleiro();

		case "->":

		case "^^":

		case "v":

		case "vv":

		case "<-":

		case "<<":

		case ">>":

			break;

		default:
			break;
		}
	}

	public void renderizaMurosInternos() {

		int ultiPos = matriz.length - 1;

		if ((matriz[0][ultiPos].equals("M") || matriz[0][ultiPos].equals("D"))
				&& (matriz[ultiPos][ultiPos].equals("M") || matriz[ultiPos][ultiPos].equals("D"))) {

			for (int i = 0; i < 5; i++) {
				matriz[matriz.length - 4][i] = "M";
			}

			for (int i = 1; i < 6; i++) {
				matriz[matriz.length - 7][i] = "M";
			}

			for (int i = 0; i < 5; i++) {
				matriz[i][matriz.length - 4] = "M";
			}

			for (int i = 0; i < 5; i++) {
				matriz[8][i] = "M";
			}

		}

		if ((matriz[0][0].equals("M") || matriz[0][0].equals("D"))
				&& (matriz[ultiPos][0].equals("M") || matriz[ultiPos][0].equals("D"))) {

			for (int i = ultiPos; i > 4; i--) {
				matriz[matriz.length - 4][i] = "M";
			}

			for (int i = 0; i < 5; i++) {
				matriz[i][ultiPos - 4] = "M";
			}

			for (int i = 1; i < 6; i++) {
				matriz[i][2] = "M";
			}

			for (int i = ultiPos; i > 4; i--) {
				matriz[ultiPos - 1][i] = "M";
			}

		}

		if ((matriz[0][0].equals("M") || matriz[0][0].equals("D"))
				&& (matriz[0][ultiPos].equals("M") || matriz[0][ultiPos].equals("D"))) {

			for (int i = ultiPos; i > 4; i--) {
				matriz[i][1] = "M";
			}

			for (int i = 0; i < 5; i++) {
				matriz[2][i] = "M";
			}

			for (int i = ultiPos; i > 4; i--) {
				matriz[i][ultiPos - 1] = "M";
			}

			for (int i = ultiPos; i > 4; i--) {
				matriz[3][i] = "M";
			}

		}

		if ((matriz[ultiPos][0].equals("M") || matriz[ultiPos][0].equals("D"))
				&& (matriz[ultiPos][ultiPos].equals("M") || matriz[ultiPos][ultiPos].equals("D"))) {

			for (int i = 0; i < 5; i++) {
				matriz[ultiPos - 3][i] = "M";
			}

			for (int i = 1; i < 6; i++) {
				matriz[3][i] = "M";
			}

			for (int i = 0; i < 5; i++) {
				matriz[i][6] = "M";
			}

			for (int i = 1; i < 6; i++) {
				matriz[i][ultiPos] = "M";
			}

		}

	}

	public void randomizaMuroePorta() {

		door = new Porta(this.posicaoXPorta, this.posicaoYPorta);
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
		} while (!(this.matriz[xis][ips].equals("-")) & (this.matriz[xis][ips].equals("D"))
				& (this.matriz[xis][ips].equals("M")) & (this.matriz[xis][ips].equals("B"))
				& (this.matriz[xis][ips].equals("k")) & (this.matriz[xis][ips].equals("S")));

		this.matriz[xis][ips] = "A";

		this.posicaoXAgente = ips;
		this.posicaoYAgente = xis;

		System.out.println("Posicao do agente: " + posicaoAgente());
		System.out.println("Posicao porta : " + posicaoPorta());
		System.out.println();

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
		int tamMat = this.matriz.length;

		for (int i = 0; i < num; i++) {
			do {

				x = this.random.nextInt(tamMat);
				y = this.random.nextInt(tamMat);

			} while ((this.matriz[y][x].equals("M")) && (visinhosContem(y, x, "B") == false)
					&& (this.matriz[y][x].equals("D")));

			if ((!this.matriz[y][x].equals("M")) && (!this.matriz[y][x].equals("D"))
					&& (visinhosContem(x, y, "B") == false)) {
				this.matriz[y][x] = "B";
			}

		}

	}

	public Boolean visinhosContem(int x, int y, String msn) {

		String[] vis = visinhos(x, y);

		boolean contem = false;

		for (int i = 0; i < vis.length; i++) {
			if (vis[i].equals(msn)) {
				contem = true;
				break;
			} else {
				contem = false;
			}
		}

		return contem;

	}

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

	public void inseriBaus() {
		int ultiPos = matriz.length - 1;
		int num = 0;

		if ((matriz[0][ultiPos].equals("M") || matriz[0][ultiPos].equals("D"))
				&& (matriz[ultiPos][ultiPos].equals("M") || matriz[ultiPos][ultiPos].equals("D"))) {

			for (int i = 0; i < 4; i++) {
				num = this.random.nextInt(matriz.length);
				if (this.matriz[num][ultiPos - 1].equals("-")) {
					matriz[num][ultiPos - 1] = "k";
				} else {
					matriz[i][ultiPos - 1] = "k";
				}

			}

		}

		if ((matriz[0][0].equals("M") || matriz[0][0].equals("D"))
				&& (matriz[ultiPos][0].equals("M") || matriz[ultiPos][0].equals("D"))) {

			for (int i = 0; i < 4; i++) {
				num = this.random.nextInt(matriz.length);
				if (this.matriz[num][1].equals("-")) {
					matriz[num][1] = "k";
				} else {
					matriz[i][1] = "k";
				}

			}

		}

		if ((matriz[0][0].equals("M") || matriz[0][0].equals("D"))
				&& (matriz[0][ultiPos].equals("M") || matriz[0][ultiPos].equals("D"))) {

			for (int i = 0; i < 4; i++) {
				num = this.random.nextInt(matriz.length);
				if (this.matriz[1][num].equals("-")) {
					matriz[1][num] = "k";
				} else {
					matriz[1][i] = "k";
				}

			}

		}

		if ((matriz[ultiPos][0].equals("M") || matriz[ultiPos][0].equals("D"))
				&& (matriz[ultiPos][ultiPos].equals("M") || matriz[ultiPos][ultiPos].equals("D"))) {

			for (int i = 0; i < 4; i++) {
				num = this.random.nextInt(matriz.length);
				if (this.matriz[ultiPos - 1][num].equals("-")) {
					matriz[ultiPos - 1][num] = "k";
				} else {
					matriz[ultiPos - 1][i] = "k";

				}

			}

		}
	}

	public void inseriSacoMoedas(int qnt) {
		int x = 0;
		int y = 0;

		for (int i = 0; i < qnt; i++) {

			do {
				x = this.random.nextInt(matriz.length);
				y = this.random.nextInt(matriz.length);
				if (!this.matriz[x][y].equals("k")
						& (!this.matriz[x][y].equals("B") & (!this.matriz[x][y].equals("M")))) {
					matriz[x][y] = "S";
				}

			} while (this.matriz[x][y].equals("-") & (this.matriz[x][y].equals("B")) & (this.matriz[x][y].equals("M"))
					& (this.matriz[x][y].equals("k")));

		}

	}

	public static void main(String[] args) {
		Tabuleiro tab = new Tabuleiro(10);

		tab.randomizaMuroePorta();
		tab.mostrarTabuleiro();
		System.out.println("\n=======");
		tab.renderizaMurosInternos();
		tab.mostrarTabuleiro();
		System.out.println("\n=======");
		tab.inserirBuracos(12);
		tab.mostrarTabuleiro();
		System.out.println("\n=======");
		tab.inseriBaus();
		tab.mostrarTabuleiro();
		System.out.println("\n=======");
		tab.inseriSacoMoedas(28);
		tab.mostrarTabuleiro();
		System.out.println("\n=======");
		tab.insereAgente();
		tab.mostrarTabuleiro();
		System.out.println("\n=======");

		ArrayList<SacoMoeda> lista = tab.getSacos();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println("Position: " + lista.get(i).getPositionY() + " " + lista.get(i).getPositionX()
					+ " Valor: " + lista.get(i).getValue());
		}

	}

}
