package logica.fabricaPlanificadores;

import logica.planificacion.Planificacion;
import logica.planificacion.Prioridad;

public class GeneradorPrioridad implements GeneradorPlanificador {

	@Override
	public Planificacion getPlanificador() {
		return new Prioridad();
	}

}
