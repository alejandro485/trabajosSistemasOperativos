package logica.fabricaPlanificadores;

import logica.planificacion.Planificacion;
import logica.planificacion.SJF;

public class GeneradorSJF implements GeneradorPlanificador {

	@Override
	public Planificacion getPlanificador() {
		return new SJF();
	}

}
