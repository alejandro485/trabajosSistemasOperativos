package logica.fabricaPlanificadores;

import java.util.HashMap;

import logica.planificacionDisco.Planificacion;

public class Generador {
	private HashMap<String, GeneradorPlanificador> generadores;
	
	public Generador() {
		generadores = new HashMap<String, GeneradorPlanificador>();
		generadores.put("FIFO", new GeneradorFIFO());
		generadores.put("SJF", new GeneradorSJF());
	}
	
	public Planificacion getPlanificador(String planificador){
		if(generadores.containsKey(planificador)){
			return generadores.get(planificador).getPlanificador();
		}
		return null;
	}
	
}
