package logica.planificacionDisco;

public class FIFO extends Planificacion {

	public void agregar(String nombre, int rafaga, int llegada) {
		Nodo nodo = new Nodo(nombre);
		nodo.setRafaga(rafaga);
		nodo.setLlegada(llegada);
		Nodo pre = this.cabeza;
		Nodo pos = this.cabeza.getSig();
		while (!pos.equals(this.cabeza)) {
			pre = pos;
			pos = pos.getSig();
		}
		nodo.setSig(this.cabeza);
		pre.setSig(nodo);
	}
}
