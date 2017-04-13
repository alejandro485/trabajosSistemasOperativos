package logica.fabricaPlanificadores;

import logica.planificacionDisco.FIFO;
import logica.planificacionDisco.Planificacion;

public class GeneradorFIFO implements GeneradorPlanificador {

	@Override
	public Planificacion getPlanificador() {
		return new FIFO();
	}

}
