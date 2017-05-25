package logica.fabricaPlanificadores;

import logica.planificacion.FIFO;
import logica.planificacion.Planificacion;

public class GeneradorFIFO implements GeneradorPlanificador {

	@Override
	public Planificacion getPlanificador() {
		return new FIFO();
	}

}
