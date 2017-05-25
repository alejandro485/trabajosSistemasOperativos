package Vista.Comando;

import java.awt.Color;
import java.awt.Graphics;

import Vista.Medidas;

public class PintarEspera implements ComandoGraficar {
	
	private int inicio;
	private int llegada;
	private int tiempoActual;
	private int iteracion;
	
	public PintarEspera() {
		this.inicio = 0;
		this.llegada = 0;
		this.tiempoActual = 0;
		this.iteracion = 0;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public void setLlegada(int llegada) {
		this.llegada = llegada;
	}

	public void setTiempoActual(int tiempoActual) {
		this.tiempoActual = tiempoActual;
	}

	public void setIteracion(int iteracion) {
		this.iteracion = iteracion;
	}

	@Override
	public void pintar(Graphics g) {
		if(this.llegada < this.tiempoActual){
			g.setColor(Color.black);
			int anchoEspera = 0;
			if (this.inicio < this.tiempoActual) {
				anchoEspera = this.inicio - this.llegada;
			} else {
				anchoEspera = this.tiempoActual - this.llegada;
			}
			g.fillRect(
				(this.llegada + 2) * Medidas.SEGMENTO_HORIZONTAL,
				(this.iteracion + 2) * Medidas.SEGMENTO_VERTICAL + 7,
				Medidas.SEGMENTO_HORIZONTAL * anchoEspera,
				Medidas.SEGMENTO_VERTICAL * 1/3);
		}
	}

}
