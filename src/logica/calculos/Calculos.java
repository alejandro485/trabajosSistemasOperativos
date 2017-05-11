package logica.calculos;

import java.util.HashMap;
import java.util.Random;

import logica.planificacionDisco.Nodo;
import logica.planificacionDisco.Planificacion;

public class Calculos {
	private Nodo cabeza;
	private Planificacion planificador;
	private int contador;
	private int anteriorLlegada;
	private int finalAnt;
	private HashMap<String, RegistroCalculo> registros;
	
	public Calculos() {
		this.contador = 0;
		this.anteriorLlegada = 0;
		this.registros = new HashMap<String, RegistroCalculo>();
	}
	
	public Planificacion getPlanificador() {
		return planificador;
	}
	
	public HashMap<String, RegistroCalculo> getRegistros(){
		return this.registros;
	}
	
	public void setPlanificador(Planificacion planificador) {
		this.planificador = planificador;
		this.cabeza = this.planificador.getCabeza();
	}
	
	public void agregar() {
	    Random rand = new Random();
	    int randomNum = rand.nextInt(20);
	    int rafagaAnt = 9;
	    int ayudaRafaga = 0;
	    for(int i = 0; i< randomNum; i++){
	    	ayudaRafaga = 1+rand.nextInt(rafagaAnt);
	    	rafagaAnt -= rand.nextInt(3);
	    	if(rafagaAnt < 4){
	    		rafagaAnt = 9;
	    	}
	    	planificador.agregar("p"+contador, ayudaRafaga, anteriorLlegada);
	    	anteriorLlegada += rand.nextInt(3);
	    	contador++;
	    }
	}

	public void calcular() {
		Nodo nodo = planificador.remover();
		RegistroCalculo rc;
		while(!nodo.equals(this.cabeza)) {
			if(this.registros.containsKey(nodo.getNombre())){
				rc = this.registros.get(nodo.getNombre());
				nodo.setLlegada(rc.getFinalizacion());
			} else {
				rc = new RegistroCalculo(nodo.getNombre(), nodo.getLlegada());
				this.registros.put(nodo.getNombre(), rc);
			}
			NodoCalculo nc = new NodoCalculo();
			nc.setRafaga(nodo.getRafaga());
			nc.setLlegada(nodo.getLlegada());
			if(nodo.getLlegada()>this.finalAnt) {
				nc.setInicio(nodo.getLlegada());
				this.finalAnt = nc.getInicio() + nc.getRafaga();
			} else {
				nc.setInicio(this.finalAnt);
				this.finalAnt += nc.getRafaga();
			}
			rc.addNodoRegistro(nc);
			nodo = planificador.remover();
		}
		this.anteriorLlegada = this.finalAnt;
	}
	
	public void limpiar() {
		this.finalAnt = 0;
		this.anteriorLlegada = 0;
		this.contador = 0;
	}
	
}
