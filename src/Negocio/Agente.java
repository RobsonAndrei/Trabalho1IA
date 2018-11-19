package Negocio;

import java.util.ArrayList;

public class Agente {
	private ArrayList<SacoMoeda> sacoList;
	private static Tabuleiro tab;
	private int poX, poY;

	public Agente() {
		tab = new Tabuleiro(10);
		tab.randomizaMuroePorta();
		tab.mostrarTabuleiro();
		System.out.println("   =======   ");
		tab.renderizaMurosInternos();
		tab.mostrarTabuleiro();
		System.out.println("   =======   ");
		tab.inserirBuracos(8);
		tab.mostrarTabuleiro();
		System.out.println("   =======   ");
		tab.inseriBaus();
		tab.mostrarTabuleiro();
		System.out.println("   =======   ");
		tab.inseriSacoMoedas(16);
		tab.mostrarTabuleiro();
		System.out.println("   =======   ");
		tab.insereAgente();
		tab.mostrarTabuleiro();
		System.out.println("   =======   ");

	}

	public void percebeAgente() {
		int x = tab.posiCaoXAgente();
		int y = tab.posiCaoYAgente();

	}

	public static void main(String[] args) {
		Agente ag = new Agente();

		int x = tab.posiCaoXAgente();
		int y = tab.posiCaoYAgente();

		System.out.println("Visinhos do agente");
		for (int i = 0; i < tab.visinhos(x, y).length; i++) {
			System.out.println(tab.visinhos(x, y)[i]);
		}

		ArrayList<SacoMoeda> lisSac = new ArrayList<>();
		lisSac = tab.getSacos();
		int total = 0;
		for (int i = 0; i < lisSac.size(); i++) {
			System.out.println("Position: " + lisSac.get(i).getPositionY() + "" + lisSac.get(i).getPositionX()
					+ " Valor: " + lisSac.get(i).getValue());
			total = total + lisSac.get(i).getValue();
		}
		System.out.println("Total de Moedas: " + total);
		System.out.println("Moedas por baú: " + total / 4);

	}

}
