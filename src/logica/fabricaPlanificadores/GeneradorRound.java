package logica.fabricaPlanificadores;

import logica.planificacion.Planificacion;
import logica.planificacion.Round;

public class GeneradorRound implements GeneradorPlanificador {

	@Override
	public Planificacion getPlanificador() {
		return new Round();
	}

}
