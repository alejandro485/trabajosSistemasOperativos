package logica.calculos;


import java.util.ArrayList;
import java.util.Random;

import logica.planificacionDisco.Nodo;
import logica.planificacionDisco.Planificacion;

public class Calculos {
	private Nodo cabeza;
	private Planificacion planificador;
	private ArrayList<RegistroCalculo> registros;
	private int contador;
	private int anteriorLlegada;
	
	public Calculos() {
		this.contador = 0;
		this.anteriorLlegada = 0;
		registros = new ArrayList<RegistroCalculo>();
	}
	
	public Planificacion getPlanificador() {
		return planificador;
	}

	public ArrayList<RegistroCalculo> getRegistros() {
		return registros;
	}
	
	public void setPlanificador(Planificacion planificador) {
		this.planificador = planificador;
		this.cabeza = this.planificador.getCabeza();
	}
	
	public void agregar() {
	    Random rand = new Random();
	    int randomNum = rand.nextInt(10);
	    for(int i = 0; i< randomNum; i++){
	    	planificador.agregar("p"+contador, 1+rand.nextInt(5), anteriorLlegada);
	    	anteriorLlegada += rand.nextInt(5);
	    	contador++;
	    }
	}

	public void calcular() {
		int finalAnt = 0;
		Nodo nodo = planificador.remover();
		while(nodo != this.cabeza){
			RegistroCalculo rc = new RegistroCalculo(nodo, finalAnt);
			finalAnt = rc.getTranscurrido();
			registros.add(rc);
			nodo = planificador.remover();
		}
	}
	
	public void limpiar() {
		this.registros.clear();
		this.anteriorLlegada = 0;
		this.contador = 0;
	}
	
}
