package Negocio;

public class Neoronio {
	private static double w0, w1, w2, w3, w4;

	public static double calculaV(double x1, double x2, double x3, double x4) {
		return w0 + (w1 * x1) + (w2 * x2) + (w3 * x3) + (w4 * x4);
	}

	public static double funcTransfLogis(double resultV) {// valores entre 0 e 1
		return (1 / (1 + Math.exp(-resultV)));
	}

	public static double tangHiper(double resultV) {// valores entre -1 e 1
		return (Math.tan(resultV));
	}

	public double getW0() {
		return w0;
	}

	public double getW1() {
		return w1;
	}

	public double getW2() {
		return w2;
	}

	public double getW3() {
		return w3;
	}

	public double getW4() {
		return w4;
	}

	public void setW0(double w0) {
		this.w0 = w0;
	}

	public void setW1(double w1) {
		this.w1 = w1;
	}

	public void setW2(double w2) {
		this.w2 = w2;
	}

	public void setW3(double w3) {
		this.w3 = w3;
	}

	public void setW4(double w4) {
		this.w4 = w4;
	}
	
	public static void main(String[] args) {
		System.out.println(funcTransfLogis(calculaV(0, 0.1, 0.5, .9)));
	}

}
