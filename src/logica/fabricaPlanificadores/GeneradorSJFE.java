package logica.fabricaPlanificadores;

import logica.planificacionDisco.Planificacion;
import logica.planificacionDisco.SJFE;

public class GeneradorSJFE implements GeneradorPlanificador {

	@Override
	public Planificacion getPlanificador() {
		return new SJFE();
	}

}
