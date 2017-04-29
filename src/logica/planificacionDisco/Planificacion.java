package logica.planificacionDisco;

public abstract class Planificacion {

	protected Nodo cabeza;

	abstract public void agregar(String nombre, int ubicacion, int llegada);

	public Nodo remover() {
		Nodo cabSig = this.cabeza.getSig();
		this.cabeza.setSig(cabSig.getSig());
		return cabSig;
	}

	public Planificacion() {
		this.cabeza = new Nodo("Cabeza", 0, 0);
		this.cabeza.setSig(this.cabeza);
	}

	public Nodo getCabeza() {
		return this.cabeza;
	}
	
}
