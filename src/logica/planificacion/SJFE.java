package logica.planificacion;

public class SJFE extends Planificacion {

	@Override
	public void agregar(Nodo nodo) {
		Nodo pre = this.procesoAnterior;
		Nodo pos = this.procesoActual;
		if(this.tiempoEnProceso > 0 && this.tiempoEnProceso < pos.getRafaga()) {
			int rafagaRestante = pos.getRafaga() - this.tiempoEnProceso;
			if(rafagaRestante > nodo.getRafaga()){
				pos.setRafaga(pos.getRafaga() - rafagaRestante);
				Nodo corte = new Nodo(pos.getNombre());
				corte.setRafaga(rafagaRestante);
				corte.setPrioridad(pos.getPrioridad());
				corte.setLlegada(nodo.getLlegada());
				corte.setSig(pos.getSig());
				nodo.setSig(corte);
				pos.setSig(nodo);
				return;
			}
		}
		if(this.tiempoEnProceso != 0){
			pre = pos;
			pos = pos.getSig();
		}
		while(!pos.equals(this.cabeza)) {
			if (nodo.getRafaga() < pos.getRafaga()) {
				nodo.setSig(pos);
				pre.setSig(nodo);
				return;
			}
			pre = pos;
			pos = pos.getSig();
		}
		nodo.setSig(pos);
		pre.setSig(nodo);
	}

}
