package logica.planificacion;

public class Round extends Planificacion {
	
	private int quiebre;
	
	public Round() {
		super();
		this.quiebre = 4;
	}

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
	
	@Override
	public void temporizador(int tiempoActual) {
		this.procesoActual = null;
		Nodo pre = this.cabeza;
		Nodo pos = this.cabeza.getSig();
		int transcurrido = pos.getLlegada();
		this.procesoAnterior = pre;
		while (!pos.equals(this.cabeza)) {
			if (pos.getLlegada() > transcurrido){
				transcurrido = pos.getLlegada();
			}
			this.tiempoEnProceso = tiempoActual - transcurrido;
			if(this.tiempoEnProceso == this.quiebre && this.tiempoEnProceso < pos.getRafaga()) {
				System.out.println("proceso roto: "+pos.getNombre());
				int rafagaRestante = pos.getRafaga() - this.tiempoEnProceso;
				pos.setRafaga(pos.getRafaga() - rafagaRestante);
				Nodo corte = new Nodo(pos.getNombre());
				corte.setRafaga(rafagaRestante);
				corte.setPrioridad(pos.getPrioridad());
				corte.setLlegada(tiempoActual);
				if (pos.getSig().equals(this.cabeza)) {
					corte.setSig(pos.getSig());
					pos.setSig(corte);
				} else {
					corte.setSig(pos.getSig().getSig());
					pos.getSig().setSig(corte);
				}
			}
			if (transcurrido <= tiempoActual && tiempoActual < transcurrido + pos.getRafaga()) {
				this.procesoAnterior = pre;
				this.procesoActual = pos;
				return;
			}
			transcurrido += pos.getRafaga();
			this.procesoAnterior = pre;
			pre = pos;
			pos = pos.getSig();
		}
		this.procesoActual = pre;
	}

}
