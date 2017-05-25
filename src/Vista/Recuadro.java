package Vista;

public class Recuadro {
	private int pocisionX;
	private int pocisionY;
	private int ancho;
	private int alto;
	
	public Recuadro() {
		this.pocisionX = 0;
		this.pocisionY = 0;
		this.alto = 0;
		this.ancho = 0;
	}

	public void setPocisionX(int pocisionX) {
		this.pocisionX = pocisionX;
	}

	public void setPocisionY(int pocisionY) {
		this.pocisionY = pocisionY;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getPocisionX() {
		return pocisionX;
	}

	public int getPocisionY() {
		return pocisionY;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	public void moverDerecha(){
		if (this.ancho - Medidas.ANCHO - this.pocisionX < Medidas.DIFERENCIAL_HORIZONTAL) {
			this.pocisionX += this.ancho - Medidas.ANCHO - this.pocisionX;
		} else {
			this.pocisionX += Medidas.DIFERENCIAL_HORIZONTAL;
		}
	}
	
	public void moverIzquierda(){
		if(this.pocisionX < Medidas.DIFERENCIAL_HORIZONTAL) {
			this.pocisionX = 0;
		} else {
			this.pocisionX -= Medidas.DIFERENCIAL_HORIZONTAL;
		}
	}
	
	public void moverArriba(){
		if (this.pocisionY < Medidas.DIFERENCIAL_VERTICAL) {
			this.pocisionY = 0;
		} else {
			this.pocisionY -= Medidas.DIFERENCIAL_VERTICAL;
		}
	}
	
	public void moverAbajo(){
		if (this.alto - Medidas.ALTO - this.pocisionY < Medidas.DIFERENCIAL_VERTICAL) {
			this.pocisionY += this.alto - Medidas.ALTO - this.pocisionY;
		} else {
			this.pocisionY += Medidas.DIFERENCIAL_VERTICAL;
		}
	}
	
	public void calcular(int lineas, int columnas){
		int width = (columnas + 4) * Medidas.SEGMENTO_HORIZONTAL;
		int height = (lineas + 7) * Medidas.SEGMENTO_VERTICAL;
		this.alto = Medidas.ALTO;
		this.ancho = Medidas.ANCHO;
		if(this.ancho < width) {
			this.ancho = width;
		}
		if(this.alto < height){
			this.alto = height;
		}
		this.pocisionX = 0;
		this.pocisionY = 0;
	}

}
