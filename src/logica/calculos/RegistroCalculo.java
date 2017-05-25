package logica.calculos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class RegistroCalculo {
	private int llegada;
	private int rafada;
	private int espera;
	private int finalizacion;
	private int transcurrido;
	private int bloqueado;
	private int prioridad;
	private String nombre;
	private ArrayList<NodoCalculo> nodos;
	private Color color;
	
	public RegistroCalculo(String nombre, int llegada, int prioridad) {
		this.nombre = nombre;
		this.llegada = llegada;
		this.prioridad = prioridad;
		this.rafada = 0;
		this.espera = 0;
		this.finalizacion = 0;
		this.transcurrido = 0;
		this.bloqueado = 0;
		Random rand = new Random();
		this.nodos = new ArrayList<NodoCalculo>();
		this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
	
	public Color getColor(){
		return this.color;
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
	
	public ArrayList<NodoCalculo> getNodos(){
		return this.nodos;
	}
	
	public int getBloqueado() {
		return bloqueado;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void addNodoRegistro(NodoCalculo nodo){
		this.rafada += nodo.getRafaga();
		this.espera += (nodo.getInicio() - nodo.getLlegada());
		this.transcurrido = this.finalizacion - this.llegada;
		this.finalizacion = nodo.getInicio() + nodo.getRafaga();
		this.bloqueado = nodo.getBloqueado();
		this.nodos.add(nodo);
	}
	
	
}
