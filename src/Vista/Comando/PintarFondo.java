package Vista.Comando;

import java.awt.Color;
import java.awt.Graphics;

public class PintarFondo implements ComandoGraficar {
	
	private int width;
	private int height;
	
	public PintarFondo() {
		this.width = 0;
		this.height = 0;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void pintar(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0,0 , this.width, this.height);
	}

}
