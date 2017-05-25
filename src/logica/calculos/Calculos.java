package logica.calculos;

import java.util.HashMap;
import java.util.Random;

import logica.planificacion.Nodo;
import logica.planificacion.Planificacion;

public class Calculos {
	private Nodo cabeza;
	private Nodo bloqueado;
	private Planificacion planificador;
	private int tiempoTotal;
	private int contador;
	private HashMap<String, RegistroCalculo> registros;
	
	public Calculos() {
		this.tiempoTotal = 0;
		this.contador = 0;
		this.registros = new HashMap<String, RegistroCalculo>();
	}

	public HashMap<String, RegistroCalculo> getRegistros(){
		return this.registros;
	}
	
	public void setPlanificador(Planificacion planificador) {
		this.planificador = planificador;
		this.cabeza = this.planificador.getCabeza();
		this.bloqueado = this.planificador.getBloqueado();
	}
	
	public void agregar() {
	    Random rand = new Random();
	    int totalProcesos = 20;
	    int procesosEnMomento = 0;
	    int rafaga = 0;
	    int prioridad = 0;
	    this.contador = 0;
	    while (totalProcesos > 0) {
	    	procesosEnMomento = 1 + rand.nextInt(3);
	    	for (int i = 0; i < procesosEnMomento; i++) {
		    	this.planificador.temporizador(this.tiempoTotal);
	    		rafaga = 1 + rand.nextInt(12);
	    		prioridad = 1 + rand.nextInt(3);
	    		Nodo nodo = new Nodo(""+this.contador);
	    		nodo.setLlegada(this.tiempoTotal);
	    		nodo.setRafaga(rafaga);
	    		nodo.setPrioridad(prioridad);
	    		this.planificador.agregar(nodo);
	    		this.contador+= 1;
	    	}
	    	if (rand.nextBoolean()) {
	    		this.planificador.temporizador(this.tiempoTotal);
	    		this.planificador.bloquear(this.tiempoTotal);
	    	}
	    	if (rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean()) {
		    	this.planificador.temporizador(this.tiempoTotal);
	    		this.planificador.desbloquear(this.tiempoTotal);
	    	}
	    	totalProcesos -= procesosEnMomento;
	    	this.tiempoTotal += 1;
	    }
	    while(!this.bloqueado.getSig().equals(this.bloqueado)){
	    	if (rand.nextBoolean()) {
		    	this.planificador.temporizador(this.tiempoTotal);
	    		this.planificador.desbloquear(this.tiempoTotal);
	    	}
		    this.bloqueado = this.planificador.getBloqueado();
		    this.tiempoTotal += 1;
	    }
	    while(this.tiempoTotal < 200) {
	    	this.planificador.temporizador(this.tiempoTotal);	    	
	    	this.tiempoTotal += 1;
	    }
	}

	public void calcular() {
		Nodo nodo = planificador.remover();
		int finalAnterior = 0;
		RegistroCalculo rc;
		while(!nodo.equals(this.cabeza)) {
			NodoCalculo nc = new NodoCalculo();
			if(this.registros.containsKey(nodo.getNombre())){
				rc = this.registros.get(nodo.getNombre());
				nc.setBloqueado(rc.getFinalizacion());
			} else {
				rc = new RegistroCalculo(nodo.getNombre(), nodo.getLlegada(), nodo.getPrioridad());
				this.registros.put(nodo.getNombre(), rc);
				nc.setBloqueado(nodo.getLlegada());
			}
			nc.setRafaga(nodo.getRafaga());
			nc.setLlegada(nodo.getLlegada());
			if(nodo.getLlegada() > finalAnterior) {
				nc.setInicio(nodo.getLlegada());
				finalAnterior = nc.getInicio() + nc.getRafaga();
			} else {
				nc.setInicio(finalAnterior);
				finalAnterior += nc.getRafaga();
			}
			rc.addNodoRegistro(nc);
			nodo = planificador.remover();
		}
		this.tiempoTotal = finalAnterior;
	}
	
	public void limpiar() {
		this.registros.clear();
		
	}
	
}
