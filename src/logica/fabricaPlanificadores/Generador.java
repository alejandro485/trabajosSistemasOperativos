package logica.fabricaPlanificadores;

import java.util.HashMap;

import logica.planificacion.Planificacion;

public class Generador {
	private HashMap<String, GeneradorPlanificador> generadores;
	
	public Generador() {
		generadores = new HashMap<String, GeneradorPlanificador>();
		generadores.put("FIFO", new GeneradorFIFO());
		generadores.put("SJF", new GeneradorSJF());
		generadores.put("SJFE", new GeneradorSJFE());
		generadores.put("Prioridad", new GeneradorPrioridad());
		generadores.put("Round", new GeneradorRound());
	}
	
	public Planificacion getPlanificador(String planificador){
		if(generadores.containsKey(planificador)){
			return generadores.get(planificador).getPlanificador();
		}
		return null;
	}
	
}
