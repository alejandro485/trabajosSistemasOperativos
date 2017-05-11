package logica.planificacionDisco;

public class Nodo {
	private String nombre;
	private int rafaga;
	private int llegada;
	private Nodo sig;

	public Nodo(String nombre) {
		this.nombre = nombre;
		this.llegada = 0;
		this.rafaga = 0;
		this.sig = null;
	}
	
	public void setRafaga(int rafaga){
		this.rafaga = rafaga;
	}
	
	public void setLlegada(int llegada){
		this.llegada = llegada;
	}

	public void setSig(Nodo sig) {
		this.sig = sig;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Nodo getSig() {
		return sig;
	}

	public String getNombre() {
		return nombre;
	}

	public int getRafaga() {
		return rafaga;
	}

	public int getLlegada() {
		return llegada;
	}
	
}
