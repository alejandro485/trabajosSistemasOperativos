package Vista.Comando;

import java.awt.Color;
import java.awt.Graphics;

import Vista.Medidas;

public class PintarNombreProceso implements ComandoGraficar {

	private String nombre;
	private int iteracion;

	public PintarNombreProceso() {
		this.nombre = "";
		this.iteracion = 0;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIteracion(int iteracion) {
		this.iteracion = iteracion;
	}

	@Override
	public void pintar(Graphics g) {
		g.setColor(Color.black);
		g.drawString(this.nombre, Medidas.SEGMENTO_HORIZONTAL - 7,
			(this.iteracion + 3) * Medidas.SEGMENTO_VERTICAL - 7);
	}

}
