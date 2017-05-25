package logica.calculos;

public class NodoCalculo {
	private int rafaga;
	private int llegada;
	private int inicio;
	private int bloqueado;
	
	public NodoCalculo() {
		this.rafaga = 0;
		this.llegada = 0;
		this.inicio = 0;
		this.bloqueado = 0;
	}
	
	public int getRafaga() {
		return rafaga;
	}
	public void setRafaga(int rafaga) {
		this.rafaga = rafaga;
	}
	public int getLlegada() {
		return llegada;
	}
	public void setLlegada(int llegada) {
		this.llegada = llegada;
	}
	public int getInicio() {
		return inicio;
	}
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public int getBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(int bloqueado) {
		this.bloqueado = bloqueado;
	}

}
