package Vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.Timer;

import logica.calculos.NodoCalculo;
import logica.calculos.RegistroCalculo;

public class CanvasGantt extends Canvas implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	//variables de entorno grafico
	private BufferedImage imagen;
	private Graphics graficas;
	
	//variables de proporciones en pantalla
	private int segmentoHorizontal;
	private int segmentoVertical;
	
	//tamaño de la imagen generada
	private int wt;
	private int ht;
	private int difH;
	private int difV;
	private int pocX;
	private int pocY;
	
	//datos a pintar
	private HashMap<String, RegistroCalculo> registros;
	
	//variables de timer
	private int tiempoActual;
	private boolean finalizadoProcesos;
	private Timer timer;
	
	public CanvasGantt() {
		this.setBackground(Color.white);
		this.segmentoHorizontal = 30;
		this.segmentoVertical = 20;
		this.wt = this.getWidth();
		this.ht = this.getHeight();
		this.registros = new HashMap<String, RegistroCalculo>();
		this.difH = 150;
		this.difV = 100;
		this.pocX = 0;
		this.pocY = 0;
	}
	
	public void setRegistro(HashMap<String, RegistroCalculo> registros) {
		this.registros = registros;
		this.tiempoActual = 0;
		this.finalizadoProcesos = false;
		this.timer = new Timer (100, this);
		this.timer.start();
	}
	
	public void update(Graphics g) {
		g.drawImage(pintarGantt(), 0, 0, this);
		graficas.clearRect(0, 0, wt, ht);
	}
	
	private BufferedImage pintarGantt() {
		int lines = registros.size();
		int columns = 0;
		for(Entry<String, RegistroCalculo> entry : registros.entrySet()) {
			if(entry.getValue().getFinalizacion() > columns){
			    columns = entry.getValue().getFinalizacion();
			}
		}
		int width = (columns + 4) * segmentoHorizontal;
		int height = (lines + 7) * segmentoVertical;
		this.wt = this.getWidth();
		this.ht = this.getHeight();
		if(this.wt < width) {
			this.wt = width;
		}
		if(this.ht < height){
			this.ht = height;
		}
		imagen = new BufferedImage(this.wt, this.ht, BufferedImage.TYPE_3BYTE_BGR);
		graficas = imagen.getGraphics();
		graficas.setColor(Color.white);
		graficas.fillRect(0,0 , this.wt, this.ht);
		int i = 0;
		for(i = 0; i<lines; i++) {
		    RegistroCalculo rc = registros.get("p"+i);
			int nodesLine = rc.getNodos().size();
			graficas.setColor(Color.black);
			graficas.drawString(rc.getNombre(), segmentoHorizontal - 7, (i + 3) * segmentoVertical - 7);
			graficas.setColor(rc.getColor());
			for(int j = 0; j<nodesLine; j++){
				NodoCalculo nc = rc.getNodos().get(j);
				// tiempo espera
				if(nc.getLlegada() < this.tiempoActual) {
					int anchoEspera = 0;
					if (nc.getInicio() < this.tiempoActual) {
						anchoEspera = nc.getInicio() - nc.getLlegada();
					} else {
						anchoEspera = this.tiempoActual - nc.getLlegada();
					}
					// pintar tiempo espera
					graficas.fillRect(
						(nc.getLlegada() + 2) * segmentoHorizontal,
						(i + 2) * segmentoVertical + 7,
						segmentoHorizontal * anchoEspera,
						segmentoVertical * 1/3);
					// pintar ejecucion
					if (nc.getInicio() < this.tiempoActual) {
						int anchoRafaga = 0;
						if (nc.getInicio()+ nc.getRafaga() < this.tiempoActual) {
							anchoRafaga = nc.getRafaga();
						} else {
							anchoRafaga = this.tiempoActual - nc.getInicio();
						}
						graficas.fillRect(
							(nc.getInicio() + 2)*segmentoHorizontal,
							(i + 2)*segmentoVertical,
							segmentoHorizontal * anchoRafaga,
							segmentoVertical);
						graficas.fillRect(
							(nc.getInicio() + 2)*segmentoHorizontal,
							(lines + 3) * segmentoVertical,
							segmentoHorizontal * anchoRafaga,
							segmentoVertical);
					}
					//verificacion terminacion
					if(i == lines - 1){
						this.finalizadoProcesos = (columns <= this.tiempoActual);
					}
				}
			}
		}
		// pintar numeros
		graficas.setColor(Color.black);
		for(i = 0; i<=columns; i++){
			graficas.drawString(i+"", segmentoHorizontal * (i + 2), segmentoVertical);
			graficas.drawString(i+"", segmentoHorizontal * (i + 2), segmentoVertical * (lines + 5));
			graficas.drawLine(
					segmentoHorizontal * (i + 2),
					segmentoVertical,
					segmentoHorizontal * (i + 2),
					segmentoVertical *(4 + lines));
		}
		graficas.drawLine(
				segmentoHorizontal * 2,
				(segmentoVertical * 3/2),
				segmentoHorizontal * (2 + columns),
				(segmentoVertical * 3/2));
		return imagen.getSubimage(this.pocX, this.pocY, this.getWidth(), this.getHeight());
	}
	
	public void cambioSeccionImagen(int dir) {
		switch (dir) {
			case 1: //arriba				
				if (this.pocY < this.difV) {
					this.pocY = 0;
				} else {
					this.pocY -= this.difV;
				}
				break;
			case 2: //abajo
				if (this.ht - this.getHeight() - this.pocY < this.difV) {
					this.pocY += this.ht - this.getHeight() - this.pocY;
				} else {
					this.pocY += this.difV;
				}
				break;
			case 3: //izquierda
				if(this.pocX < this.difH) {
					this.pocX = 0;
				} else {
					this.pocX -= this.difH;
				}
				break;
			case 4: //derecha
				if (this.wt - this.getWidth() - this.pocX < this.difH) {
					this.pocX += this.wt - this.getWidth() - this.pocX;
				} else {
					this.pocX += this.difH;
				}
				break;
		}
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
		if(this.finalizadoProcesos){
			this.timer.stop();
		}
		this.tiempoActual++;
	}

}
