package logica.fabricaPlanificadores;

import logica.planificacionDisco.Planificacion;
import logica.planificacionDisco.SJF;

public class GeneradorSJF implements GeneradorPlanificador {

	@Override
	public Planificacion getPlanificador() {
		return new SJF();
	}

}
