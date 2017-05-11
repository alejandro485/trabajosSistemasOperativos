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
	private String nombre;
	private ArrayList<NodoCalculo> nodos;
	private Color color;
	
	public RegistroCalculo(String nombre, int llegada) {
		this.nombre = nombre;
		this.llegada = llegada;
		this.rafada = 0;
		this.espera = 0;
		this.finalizacion = 0;
		this.transcurrido = 0;
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
	
	public void addNodoRegistro(NodoCalculo nodo){
		this.rafada += nodo.getRafaga();
		this.espera += (nodo.getInicio() - nodo.getLlegada());
		if(nodo.getInicio() + nodo.getRafaga() > this.finalizacion){
			this.finalizacion = nodo.getInicio() + nodo.getRafaga();
		}
		this.transcurrido = this.finalizacion - this.llegada;
		this.nodos.add(nodo);
	}
	
	
}
