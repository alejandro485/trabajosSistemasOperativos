package Vista.Comando;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;

import Vista.Medidas;
import Vista.Recuadro;
import logica.calculos.NodoCalculo;
import logica.calculos.RegistroCalculo;

public class PintarGantt implements ComandoGraficar {

	private BufferedImage imagen;
	private Graphics graficas;
	private int tiempoActual;
	private int lineas;
	private int columnas;
	private Recuadro recuadro;
	private HashMap<String, RegistroCalculo> registros;

	public PintarGantt() {
		this.tiempoActual = 0;
		this.lineas = 0;
		this.columnas = 0;
		this.recuadro = new Recuadro();
		this.registros = new HashMap<String, RegistroCalculo>();
	}

	public void setRegistros(HashMap<String, RegistroCalculo> registros) {
		this.registros = registros;
		this.lineas = registros.size();
		this.columnas = 0;
		for (Entry<String, RegistroCalculo> entry : registros.entrySet()) {
			if (entry.getValue().getFinalizacion() > this.columnas) {
				this.columnas = entry.getValue().getFinalizacion();
			}
		}
		this.recuadro.calcular(this.lineas, this.columnas);
		this.preparar();
	}

	public void setTiempoActual(int tiempoActual) {
		this.tiempoActual = tiempoActual;
	}

	public BufferedImage getImage() {
		return this.imagen.getSubimage(this.recuadro.getPocisionX(), this.recuadro.getPocisionY(),
			Medidas.ANCHO, Medidas.ALTO);
	}

	public int getColumnas() {
		return this.columnas;
	}

	public Recuadro getRecuadro() {
		return this.recuadro;
	}

	public void preparar() {
		this.imagen = new BufferedImage(this.recuadro.getAncho(), this.recuadro.getAlto(),
			BufferedImage.TYPE_3BYTE_BGR);
		this.graficas = this.imagen.getGraphics();
		PintarFondo pintarFondo = new PintarFondo();
		pintarFondo.setHeight(this.recuadro.getAlto());
		pintarFondo.setWidth(this.recuadro.getAncho());
		pintarFondo.pintar(this.graficas);

		PintarNumeros pintarNumeros = new PintarNumeros();
		pintarNumeros.setColumnas(this.columnas);
		pintarNumeros.setLineas(this.lineas);
		pintarNumeros.pintar(this.graficas);

		PintarLineasVerticales pintarlineas = new PintarLineasVerticales();
		pintarlineas.setColumnas(this.columnas);
		pintarlineas.pintar(this.graficas);
	}

	@Override
	public void pintar(Graphics g) {
		for (int i = 0; i < this.lineas; i++) {
			RegistroCalculo rc = this.registros.get(""+i);
			int nodesLine = rc.getNodos().size();

			PintarNombreProceso pintarNombreProceso = new PintarNombreProceso();
			pintarNombreProceso.setIteracion(i);
			pintarNombreProceso.setNombre(rc.getNombre());
			pintarNombreProceso.pintar(this.graficas);

			for (int j = 0; j < nodesLine; j++) {
				NodoCalculo nc = rc.getNodos().get(j);

				PintarBloqueo pintarBloqueo = new PintarBloqueo();
				pintarBloqueo.setBloqueo(nc.getBloqueado());
				pintarBloqueo.setIteracion(i);
				pintarBloqueo.setLlegada(nc.getLlegada());
				pintarBloqueo.setTiempoActual(this.tiempoActual);
				pintarBloqueo.pintar(this.graficas);

				PintarEspera pintarEspera = new PintarEspera();
				pintarEspera.setInicio(nc.getInicio());
				pintarEspera.setIteracion(i);
				pintarEspera.setLlegada(nc.getLlegada());
				pintarEspera.setTiempoActual(this.tiempoActual);
				pintarEspera.pintar(this.graficas);

				PintarEjecucion pintarEjecucion = new PintarEjecucion();
				pintarEjecucion.setColor(rc.getColor());
				pintarEjecucion.setInicio(nc.getInicio());
				pintarEjecucion.setIteracion(i);
				pintarEjecucion.setLineas(this.lineas);
				pintarEjecucion.setRafaga(nc.getRafaga());
				pintarEjecucion.setTiempoActual(this.tiempoActual);
				pintarEjecucion.pintar(this.graficas);
			}
		}
		g.drawImage(this.imagen.getSubimage(this.recuadro.getPocisionX(), this.recuadro.getPocisionY(),
				Medidas.ANCHO, Medidas.ALTO), 0, 0, null);
	}

}
