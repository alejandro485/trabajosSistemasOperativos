package logica.planificacionDisco;

public class SJF extends Planificacion {
	
	@Override
	public void agregar(String nombre, int ubicacion, int llegada) {
		Nodo nodo = new Nodo(nombre, ubicacion, llegada);
		Nodo pre = this.cabeza;
		Nodo pos = this.cabeza.getSig();
		if (pos.equals(this.cabeza)) {
			nodo.setSig(this.cabeza);
			this.cabeza.setSig(nodo);
			return;
		}
		int transcurrido = pos.getLlegada();
		while (!pos.equals(this.cabeza)) {
			if (nodo.getLlegada() <= transcurrido) {
				while (!pos.equals(this.cabeza)) {
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
			if (pos.getLlegada() > transcurrido){
				transcurrido = pos.getLlegada();
			}
			transcurrido += pos.getRafaga();
			pre = pos;
			pos = pos.getSig();
		}
		nodo.setSig(this.cabeza);
		pre.setSig(nodo);
	}

}
