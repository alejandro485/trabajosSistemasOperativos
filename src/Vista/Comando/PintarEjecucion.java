package Vista.Comando;

import java.awt.Graphics;

import Vista.Medidas;

import java.awt.Color;

public class PintarEjecucion implements ComandoGraficar {
	
	private int inicio;
	private int iteracion;
	private int lineas;
	private int rafaga;
	private int tiempoActual;
	private Color color;
	
	public PintarEjecucion() {
		this.inicio = 0;
		this.iteracion = 0;
		this.lineas = 0;
		this.rafaga = 0;
		this.tiempoActual = 0;
		this.color = Color.WHITE;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public void setIteracion(int iteracion) {
		this.iteracion = iteracion;
	}

	public void setLineas(int lineas) {
		this.lineas = lineas;
	}

	public void setRafaga(int rafaga) {
		this.rafaga = rafaga;
	}

	public void setTiempoActual(int tiempoActual) {
		this.tiempoActual = tiempoActual;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void pintar(Graphics g) {
		g.setColor(this.color);
		if (this.inicio < this.tiempoActual) {
			int anchoRafaga = 0;
			if (this.inicio + this.rafaga < this.tiempoActual) {
				anchoRafaga = this.rafaga;
			} else {
				anchoRafaga = this.tiempoActual - this.inicio;
			}
			g.fillRect(
				(this.inicio + 2) * Medidas.SEGMENTO_HORIZONTAL,
				(this.iteracion + 2) * Medidas.SEGMENTO_VERTICAL,
				Medidas.SEGMENTO_HORIZONTAL * anchoRafaga,
				Medidas.SEGMENTO_VERTICAL);
			g.fillRect(
				(this.inicio + 2) * Medidas.SEGMENTO_HORIZONTAL,
				(this.lineas + 3) * Medidas.SEGMENTO_VERTICAL,
				Medidas.SEGMENTO_HORIZONTAL * anchoRafaga,
				Medidas.SEGMENTO_VERTICAL);
		}
	}

}
