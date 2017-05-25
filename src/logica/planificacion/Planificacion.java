package logica.planificacion;

public abstract class Planificacion {

	protected Nodo cabeza;
	protected Nodo bloqueado;
	protected Nodo procesoActual;
	protected Nodo procesoAnterior;
	protected int tiempoEnProceso;
	
	abstract public void agregar(Nodo nodo);
	
	public Nodo remover() {
		Nodo cabSig = this.cabeza.getSig();
		this.cabeza.setSig(cabSig.getSig());
		return cabSig;
	}

	public Planificacion() {
		this.cabeza = new Nodo("Cabeza");
		this.cabeza.setLlegada(0);
		this.cabeza.setRafaga(0);
		this.cabeza.setSig(this.cabeza);
		
		this.bloqueado = new Nodo("bloqueado");
		this.bloqueado.setLlegada(0);
		this.bloqueado.setRafaga(0);
		this.bloqueado.setSig(this.bloqueado);
		
		this.procesoActual = this.cabeza;
		this.tiempoEnProceso = 0;
	}

	public Nodo getCabeza() {
		return this.cabeza;
	}
	
	public Nodo getBloqueado() {
		return this.bloqueado;
	}
	
	public Nodo getProcesoActual(){
		return this.procesoActual;
	}
	
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
	
	public void bloquear(int tiempoActual) {
		Nodo nodoBloq = new Nodo(this.procesoActual.getNombre());
		if (!nodoBloq.equals(this.cabeza)) {
			nodoBloq.setPrioridad(this.procesoActual.getPrioridad());
			nodoBloq.setLlegada(tiempoActual);
			nodoBloq.setRafaga(this.procesoActual.getRafaga() - this.tiempoEnProceso);
			this.procesoActual.setRafaga(this.tiempoEnProceso);
			Nodo bloq = this.bloqueado;
			Nodo sigBloq = this.bloqueado.getSig();
			while(!sigBloq.equals(this.bloqueado)) {
				bloq = sigBloq;
				sigBloq = bloq.getSig();
			}
			nodoBloq.setSig(this.bloqueado);
			bloq.setSig(nodoBloq);	
		}
	}
	
	public void desbloquear(int tiempoActual){
		Nodo nodoBloq = this.bloqueado.getSig();
		this.bloqueado.setSig(nodoBloq.getSig());
		if (!nodoBloq.equals(this.bloqueado)) {
			nodoBloq.setLlegada(tiempoActual);
			this.agregar(nodoBloq);	
		}
	}
	
}
