package logica.planificacion;

public class Nodo {
	private String nombre;
	private int rafaga;
	private int llegada;
	private Nodo sig;
	private int prioridad;

	public Nodo(String nombre) {
		this.nombre = nombre;
		this.llegada = 0;
		this.rafaga = 0;
		this.sig = null;
		this.prioridad = 0;
	}
	
	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
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
