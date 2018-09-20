package Negocio;

public class Tabuleiro {

	private String[][] matriz;

	public Tabuleiro(int tamanho) {
		this.matriz = new String[tamanho][tamanho];
	}

	public void mostrarTabuleiro() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println("\n");
		}
	}

	public void insereAgente(int x, int y) {
		matriz[x][y] = "A";
	}

	public static void main(String[] args) {
		Tabuleiro tab = new Tabuleiro(10);

		tab.insereAgente(4, 5);

		tab.mostrarTabuleiro();
	}

}
