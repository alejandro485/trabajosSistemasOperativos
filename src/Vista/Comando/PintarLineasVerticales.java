package Vista.Comando;

import java.awt.Graphics;

import Vista.Medidas;

public class PintarLineasVerticales implements ComandoGraficar {

	private int columnas;

	public PintarLineasVerticales() {
		this.columnas = 0;
	}

	public void setColumnas(int columns) {
		this.columnas = columns;
	}

	@Override
	public void pintar(Graphics g) {
		g.drawLine(Medidas.SEGMENTO_HORIZONTAL * 2, (Medidas.SEGMENTO_VERTICAL * 3 / 2),
			Medidas.SEGMENTO_HORIZONTAL * (2 + this.columnas), (Medidas.SEGMENTO_VERTICAL * 3 / 2));
	}

}
