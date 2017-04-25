package logica.calculos;

import logica.planificacionDisco.Nodo;

public class RegistroCalculo {
	private int llegada;
	private int rafada;
	private int espera;
	private int finalizacion;
	private int transcurrido;
	private String nombre;
	
	public RegistroCalculo(Nodo nodo, int finalAnt) {
		this(nodo.getNombre(), nodo.getLlegada(), nodo.getRafaga(), finalAnt);
	}

	public RegistroCalculo(String nombre,int llegada, int rafaga, int finalAnt) {
		this.llegada = llegada;
		this.rafada = rafaga;
		if(llegada > finalAnt){
			this.espera = 0;
		} else {
			this.espera = finalAnt - llegada;
		}
		this.finalizacion = this.espera + rafaga;
		this.transcurrido = this.finalizacion + llegada;
		this.nombre = nombre;
	}
	
	public int getLlegada() {
		return llegada;
	}
	
	public int getRafada() {
		return rafada;
	}
	
	public int getEspera() {
		return espera;
	}
	
	public int getFinalizacion() {
		return finalizacion;
	}
	
	public int getTranscurrido() {
		return transcurrido;
	}
	
	public String getNombre(){
		return nombre;
	}
	
}
