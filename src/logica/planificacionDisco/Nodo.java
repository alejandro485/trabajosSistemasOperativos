package logica.planificacionDisco;

public class Nodo {
	private String nombre;
	private int rafaga;
	private int llegada;
	private Nodo sig;

	public Nodo(String nombre, int ubicacion, int llegada) {
		this.nombre = nombre;
		this.rafaga = ubicacion;
		this.llegada = llegada;
		this.sig = null;
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
