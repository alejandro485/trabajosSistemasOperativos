package logica;

public class Nodo {
	private String nombre;
	private int ubicacion;
	private Nodo sig;
	
	public Nodo(String nombre, int ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.sig = null;
	}
	
	public void setSig(Nodo sig){
		this.sig = sig;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setUbicacion(int ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Nodo getSig() {
		return sig;
	}

	public String getNombre() {
		return nombre;
	}

	public int getUbicacion() {
		return ubicacion;
	}
	
}
