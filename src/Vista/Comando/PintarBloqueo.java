package Vista.Comando;

import java.awt.Color;
import java.awt.Graphics;

import Vista.Medidas;

public class PintarBloqueo implements ComandoGraficar {
	
	private int bloqueo;
	private int llegada;
	private int tiempoActual;
	private int iteracion;
	
	public PintarBloqueo() {
		this.bloqueo = 0;
		this.llegada = 0;
		this.tiempoActual = 0;
		this.iteracion = 0;
	}

	public void setBloqueo(int bloqueo) {
		this.bloqueo = bloqueo;
	}

	public void setLlegada(int llegada) {
		this.llegada = llegada;
	}
	
	public void setTiempoActual(int tiempoActual){
		this.tiempoActual = tiempoActual;
	}
	
	public void setIteracion(int iteracion){
		this.iteracion = iteracion;
	}

	@Override
	public void pintar(Graphics g) {
		if(this.bloqueo < this.tiempoActual){
			g.setColor(Color.red);
			int anchoEspera = 0;
			if (this.bloqueo < this.tiempoActual) {
				anchoEspera = this.llegada - this.bloqueo;
			} else {
				anchoEspera = this.tiempoActual - this.bloqueo;
			}
			g.fillRect(
				(this.bloqueo + 2) * Medidas.SEGMENTO_HORIZONTAL,
				(this.iteracion + 2) * Medidas.SEGMENTO_VERTICAL + 7,
				Medidas.SEGMENTO_HORIZONTAL * anchoEspera,
				Medidas.SEGMENTO_VERTICAL * 1/3);
		}
	}

}
