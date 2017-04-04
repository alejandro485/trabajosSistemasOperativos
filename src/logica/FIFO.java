package logica;

public class FIFO {
	private Nodo cabeza;
	
	public FIFO() {
		this.cabeza = new Nodo("Cabeza", 0);
		this.cabeza.setSig(this.cabeza);
	}
	
	public Nodo getCabeza(){
		return this.cabeza;
	}
	
	public void agregar(String nombre, int ubicacion){
		Nodo nodo = new Nodo(nombre, ubicacion);
		Nodo pre = this.cabeza;
		Nodo pos = this.cabeza.getSig();
		while(pos!=this.cabeza){
			pre = pos;
			pos = pos.getSig();
		}
		nodo.setSig(this.cabeza);
		pre.setSig(nodo);
	}
	
	public void remover(){
		if(this.cabeza.equals(this.cabeza.getSig())){
			return;
		}
		Nodo cabSig = this.cabeza.getSig();
		this.cabeza.setSig(cabSig.getSig());
	}
	
	public void terminar(){
		if(this.cabeza.equals(this.cabeza.getSig())){
			return;
		}
		Nodo nodo = this.cabeza.getSig();
		while(nodo != this.cabeza){
			System.out.println("proceso: "+nodo.getNombre()+" en ejecucion");
			for(int i = 0; i<1000000; i++){}
			this.cabeza.setSig(nodo.getSig());
			nodo =this.cabeza.getSig();
		}
	}
}
