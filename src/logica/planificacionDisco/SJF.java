package logica.planificacionDisco;

public class SJF extends Planificacion {

	@Override
	public void agregar(String nombre, int ubicacion, int llegada) {
		System.out.println("===> "+nombre+": "+llegada+", "+ubicacion);
		Nodo nodo = new Nodo(nombre, ubicacion, llegada);
		Nodo pre = this.cabeza;
		Nodo pos = this.cabeza.getSig();
		if (pos.equals(this.cabeza)) {
			nodo.setSig(this.cabeza);
			this.cabeza.setSig(nodo);
			return;
		}
		while (!pos.equals(this.cabeza)) {
			if (nodo.getLlegada() == pos.getLlegada()) {
				while (nodo.getLlegada() == pos.getLlegada() && !pos.equals(this.cabeza)) {
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
				return;
			}
			if (nodo.getLlegada() <= pos.getLlegada() + pos.getRafaga()) {
				do {
					pre = pos;
					pos = pos.getSig();
					if (nodo.getRafaga() < pos.getRafaga()) {
						nodo.setSig(pos);
						pre.setSig(nodo);
						return;
					}
				} while (nodo.getLlegada() <= pos.getLlegada() + pos.getRafaga() && !pos.equals(this.cabeza));
				nodo.setSig(pos);
				pre.setSig(nodo);
				return;
			}
			pre = pos;
			pos = pos.getSig();
		}
		nodo.setSig(this.cabeza);
		pre.setSig(nodo);
	}

}
