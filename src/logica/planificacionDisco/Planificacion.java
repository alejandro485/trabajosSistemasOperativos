package logica.planificacionDisco;

public interface Planificacion {
	public void agregar(String nombre, int rafaga, int llegada);
	public Nodo remover();
	public Nodo getCabeza();
}
