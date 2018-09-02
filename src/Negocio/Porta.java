package Negocio;

public class Porta {
	private boolean aberta;
	
	private int xPosition, yPosition;
	
	public Porta(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
		this.aberta = false;
	}
	
	public void setAbrePorta(boolean value) {
		this.aberta = value;
	}
	
	public boolean getStatusPorta() {
		return this.aberta;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}
	
	
	
	
}
