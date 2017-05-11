package logica.planificacionDisco;

public class SJFE extends Planificacion {

	@Override
	public void agregar(String nombre, int rafaga, int llegada) {
		Nodo nodo = new Nodo(nombre);
		nodo.setRafaga(rafaga);
		nodo.setLlegada(llegada);
		Nodo pre = this.cabeza;
		Nodo pos = this.cabeza.getSig();
		if (pos.equals(this.cabeza)) {
			nodo.setSig(this.cabeza);
			this.cabeza.setSig(nodo);
			return;
		}
		int transcurrido = 0;
		while (!pos.equals(this.cabeza)) {
			if (pos.getLlegada() > transcurrido) {
				transcurrido = pos.getLlegada();
			}
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
			} else if (nodo.getLlegada() < transcurrido + pos.getRafaga()) {
				int rafagaRestante = transcurrido + pos.getRafaga() - nodo.getLlegada();
				if (rafagaRestante > nodo.getRafaga()) {
					Nodo corte = new Nodo(pos.getNombre());
					corte.setRafaga(rafagaRestante);
					pos.setRafaga(pos.getRafaga() - rafagaRestante);
					corte.setSig(pos.getSig());
					nodo.setSig(corte);
					pos.setSig(nodo);
					return;
				}
			}
			transcurrido += pos.getRafaga();
			pre = pos;
			pos = pos.getSig();
		}
		nodo.setSig(this.cabeza);
		pre.setSig(nodo);
	}

}
