package logica.planificacion;

public class FIFO extends Planificacion {

	public void agregar(Nodo nodo) {
		Nodo pre = this.procesoActual;
		Nodo pos = this.procesoActual.getSig();
		while (!pos.equals(this.cabeza)) {
			pre = pos;
			pos = pos.getSig();
		}
		nodo.setSig(this.cabeza);
		pre.setSig(nodo);
	}
}
