package logica.planificacion;

public class SJF extends Planificacion {
	
	@Override
	public void agregar(Nodo nodo) {
		Nodo pre = this.procesoAnterior;
		Nodo pos = this.procesoActual;
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
