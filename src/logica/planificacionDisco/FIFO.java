package logica.planificacionDisco;

public class FIFO implements Planificacion{
	private Nodo cabeza;
	
	public FIFO() {
		this.cabeza = new Nodo("Cabeza", 0, 0);
		this.cabeza.setSig(this.cabeza);
	}
	
	public Nodo getCabeza(){
		return this.cabeza;
	}
	
	public void agregar(String nombre, int ubicacion, int llegada) {
		Nodo nodo = new Nodo(nombre, ubicacion, llegada);
		Nodo pre = this.cabeza;
		Nodo pos = this.cabeza.getSig();
		while(!pos.equals(this.cabeza)) {
			pre = pos;
			pos = pos.getSig();
		}
		nodo.setSig(this.cabeza);
		pre.setSig(nodo);
	}
	
	public Nodo remover() {
		Nodo cabSig = this.cabeza.getSig();
		this.cabeza.setSig(cabSig.getSig());
		return cabSig;
	}
}
