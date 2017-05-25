package Vista.Comando;

import java.awt.Color;
import java.awt.Graphics;

import Vista.Medidas;

public class PintarNumeros implements ComandoGraficar {

	private int columnas;
	private int lineas;

	public PintarNumeros() {
		this.columnas = 0;
		this.lineas = 0;
	}

	public void setColumnas(int columns) {
		this.columnas = columns;
	}

	public void setLineas(int lines) {
		this.lineas = lines;
	}

	@Override
	public void pintar(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i <= columnas; i++) {
			g.drawString(i + "", Medidas.SEGMENTO_HORIZONTAL * (i + 2), Medidas.SEGMENTO_VERTICAL);
			g.drawString(i + "", Medidas.SEGMENTO_HORIZONTAL * (i + 2), Medidas.SEGMENTO_VERTICAL * (lineas + 5));
			g.drawLine(Medidas.SEGMENTO_HORIZONTAL * (i + 2), Medidas.SEGMENTO_VERTICAL,
				Medidas.SEGMENTO_HORIZONTAL * (i + 2), Medidas.SEGMENTO_VERTICAL * (4 + lineas));
		}
	}

}
