package logica.planificacionDisco;

public class FIFO extends Planificacion {

	public void agregar(String nombre, int ubicacion, int llegada) {
		Nodo nodo = new Nodo(nombre, ubicacion, llegada);
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
