package logica;

public class FIFO {
	private Nodo cabeza;
	
	public FIFO() {
		this.cabeza = new Nodo("Cabeza", 0);
	}
	
	public Nodo getCabeza(){
		return this.cabeza;
	}
	
	public void agregar(String nombre, int ubicacion){
		Nodo nodo = new Nodo(nombre, ubicacion);
		Nodo pre = this.cabeza;
		Nodo pos = this.cabeza.getSig();
		while(pos != null){
			pre = pos;
			pos = pos.getSig();
		}
		pre.setSig(nodo);
	}
	
	public void remover(){
		if(this.cabeza.getSig() != null){
			this.cabeza.setSig(this.cabeza.getSig().getSig());
		}
	}
	
}
