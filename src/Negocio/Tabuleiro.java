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

	public String getItemPosition(int y, int x) {
		return matriz[y][x];
	}

	public String[][] getMatriz() {
		return this.matriz;
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
				System.out.print(matriz[j][i] + " ");
			}
			System.out.println("");
		}
	}

	public void moVimentaAgente(String msn) {

		switch (msn) {

		case "^":// Agente se move uma célula para frente
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
			if (x > 0) {// Se tem um buraco o Agente deve pular uma célula
				if (matriz[y][x - 1].equals("B")) {
					matriz[y][x] = "-";
					matriz[y][x - 2] = "A";
				} else {
					matriz[y][x] = "-";
					matriz[y][x - 1] = "A";

				}

			}

			mostrarTabuleiro();

		case "->":// Direita

		case "v":// Para Baixo

		case "<-":// Esquerda

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

			for (int i = ultiPos - 1; i > 3; i--) {
				matriz[4][i] = "M";
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
		} while ((!this.matriz[ips][xis].equals("-")));

		this.matriz[ips][xis] = "A";

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

			} while ((!this.matriz[y][x].equals("-")) && (this.visinhosContem(y, x, "B") == false));
			if ((this.matriz[y][x].equals("-")) && (!this.visinhosContem(y, x, "B"))) {
				this.matriz[y][x] = "B";
			}

		}

	}

	public Boolean visinhosContem(int y, int x, String msn) {

		String[] vis = visinhos(x, y);

		boolean contem = false;

		for (int i = 0; i < vis.length; i++) {
			if (vis[i].equals(msn)) {
				contem = true;

			}
		}

		return contem;

	}

	public String[] visinhos(int posY, int posX) {
		String[] vetorVisinhos = new String[1];
		int ultPos = this.matriz.length - 1;

		if (posY == 0 && posX == 0) {// Canto superior esquerdo
			vetorVisinhos = new String[3];
			vetorVisinhos[0] = matriz[posY][posX + 1];
			vetorVisinhos[1] = matriz[posY + 1][posX + 1];
			vetorVisinhos[2] = matriz[posY + 1][posX];

		} else {
			if (posY == ultPos && posX == ultPos) { // Canto inferior direito
				vetorVisinhos = new String[3];
				vetorVisinhos[0] = matriz[posY - 1][posX];
				vetorVisinhos[1] = matriz[posY - 1][posX - 1];
				vetorVisinhos[2] = matriz[posY][posX - 1];

			} else {
				if ((posY == 0) && (posX >= 1 && posX <= ultPos - 1)) { // Corredor esquerdo
					vetorVisinhos = new String[5];
					vetorVisinhos[0] = matriz[posY][posX - 1];
					vetorVisinhos[1] = matriz[posY + 1][posX - 1];
					vetorVisinhos[2] = matriz[posY + 1][posX];
					vetorVisinhos[3] = matriz[posY][posX + 1];
					vetorVisinhos[4] = matriz[posY + 1][posX + 1];
				} else {

					if ((posY >= 1 && posY <= ultPos - 1) && posX == 0) { // Corredor superior
						vetorVisinhos = new String[5];
						vetorVisinhos[0] = matriz[posY - 1][posX];
						vetorVisinhos[1] = matriz[posY - 1][posX + 1];
						vetorVisinhos[2] = matriz[posY][posX + 1];
						vetorVisinhos[3] = matriz[posY + 1][posX + 1];
						vetorVisinhos[4] = matriz[posY + 1][posX];
					} else {
						if ((posY == ultPos) && (posX >= 1 && posX <= ultPos - 1)) { // Corredor direito
							vetorVisinhos = new String[5];
							vetorVisinhos[0] = matriz[posY][posX - 1];
							vetorVisinhos[1] = matriz[posY - 1][posX - 1];
							vetorVisinhos[2] = matriz[posY - 1][posX];
							vetorVisinhos[3] = matriz[posY - 1][posX + 1];
							vetorVisinhos[4] = matriz[posY][posX + 1];
						} else {
							if (posY == ultPos && posX == 0) { // Canto Superior direito
								vetorVisinhos = new String[3];
								vetorVisinhos[0] = matriz[posY - 1][posX];
								vetorVisinhos[1] = matriz[posY - 1][posX + 1];
								vetorVisinhos[2] = matriz[posY][posX + 1];
							} else {
								if (posY == 0 && posX == ultPos) { // Canto inferior esquerdo
									vetorVisinhos = new String[3];
									vetorVisinhos[0] = matriz[posY][posX - 1];
									vetorVisinhos[1] = matriz[posY + 1][posX - 1];
									vetorVisinhos[2] = matriz[posY + 1][posX];
								} else {
									if ((posY >= 1 && posY <= ultPos - 1) && (posX == ultPos)) { // Corredor Inferior
										vetorVisinhos = new String[5];
										vetorVisinhos[0] = matriz[posY - 1][posX];
										vetorVisinhos[1] = matriz[posY - 1][posX - 1];
										vetorVisinhos[2] = matriz[posY][posX - 1];
										vetorVisinhos[3] = matriz[posY + 1][posX - 1];
										vetorVisinhos[4] = matriz[posY + 1][posX];
									} else {
										if ((posY >= 1 && posY <= ultPos - 1) && (posX >= 1 && posX <= ultPos - 1)) {
											vetorVisinhos = new String[8];
											vetorVisinhos[0] = matriz[posY - 1][posX - 1];
											vetorVisinhos[1] = matriz[posY][posX - 1];
											vetorVisinhos[2] = matriz[posY + 1][posX - 1];
											vetorVisinhos[3] = matriz[posY - 1][posX];
											vetorVisinhos[4] = matriz[posY + 1][posX];
											vetorVisinhos[5] = matriz[posY - 1][posX + 1];
											vetorVisinhos[6] = matriz[posY][posX + 1];
											vetorVisinhos[7] = matriz[posY + 1][posX + 1];
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

				do {
					num = this.random.nextInt(matriz.length);

				} while (!this.matriz[num][ultiPos - 1].equals("-"));

				matriz[num][ultiPos - 1] = "k";

			}

		}

		if ((matriz[0][0].equals("M") || matriz[0][0].equals("D"))
				&& (matriz[ultiPos][0].equals("M") || matriz[ultiPos][0].equals("D"))) {

			for (int i = 0; i < 4; i++) {
				do {
					num = this.random.nextInt(matriz.length);
				} while (!this.matriz[num][1].equals("-"));

				matriz[num][1] = "k";

			}

		}

		if ((matriz[0][0].equals("M") || matriz[0][0].equals("D"))
				&& (matriz[0][ultiPos].equals("M") || matriz[0][ultiPos].equals("D"))) {

			for (int i = 0; i < 4; i++) {

				do {
					num = this.random.nextInt(matriz.length);

				} while (!this.matriz[1][num].equals("-"));

				matriz[1][num] = "k";

			}

		}

		if ((matriz[ultiPos][0].equals("M") || matriz[ultiPos][0].equals("D"))
				&& (matriz[ultiPos][ultiPos].equals("M") || matriz[ultiPos][ultiPos].equals("D"))) {

			for (int i = 0; i < 4; i++) {
				do {
					num = this.random.nextInt(matriz.length);
				} while (!this.matriz[ultiPos - 1][num].equals("-"));

				matriz[ultiPos - 1][num] = "k";

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

			} while (!this.matriz[y][x].equals("-"));
			matriz[y][x] = "S";
		}

	}

}
