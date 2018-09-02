package Negocio;

public class SacoMoeda {
	private int value;

	private int xPosition, yPosition;

	public SacoMoeda(int valor) {
		this.value = valor;
	}

	public int getValue() {
		return value;
	}

	public void setPositions(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}

	public int getPositionX() {
		return this.xPosition;
	}

	public int getPositionY() {
		return this.yPosition;
	}

}
