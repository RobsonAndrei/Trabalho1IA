package Negocio;

public class Bau {
	private int value;
	
	private int xPosition, yPosition;

	/**
	 * @param value
	 */
	public Bau() {
		this.value = 0;
	}

	public int getValue() {
		return value;
	}

	public void recebeSacos(int value) {
		this.value = value;
	}
	
	public int retornaValue() {
		return this.value;
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
