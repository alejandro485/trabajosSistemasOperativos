package Vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

import logica.calculos.RegistroCalculo;

public class CanvasGantt extends Canvas implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	//variables de entorno grafico
	private BufferedImage imagen;
	private Graphics graficas;
	private Color[] colores;
	
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
	private ArrayList<RegistroCalculo> registros;
	
	//variables de timer
	private int tiempoActual;
	private boolean finalizadoProcesos;
	private Timer timer;
	
	public CanvasGantt() {
		this.setBackground(Color.white);
		this.segmentoHorizontal = 20;
		this.segmentoVertical = 20;
		this.wt = this.getWidth();
		this.ht = this.getHeight();
		this.registros = new ArrayList<>();
		this.difH = 50;
		this.difV = 50;
		this.pocX = 0;
		this.pocY = 0;
		colores = new Color[7];
		colores[0] = Color.red;
		colores[1] = Color.orange;
		colores[2] = Color.yellow;
		colores[3] = Color.green;
		colores[4] = Color.blue;
		colores[5] = Color.magenta;
		colores[6] = Color.cyan;
	}
	
	public void setRegistro(ArrayList<RegistroCalculo> registros) {
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
		if(lines > 0) {
			columns = registros.get(registros.size() - 1).getTranscurrido();
		} else {
			columns = 0;
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
		graficas.setColor(Color.black);
		// pintar numeros
		for(int i = 0; i<=columns; i++){
			graficas.drawString(i+"", segmentoHorizontal * (i + 2), segmentoVertical);
			graficas.drawString(i+"", segmentoHorizontal * (i + 2), segmentoVertical * (lines + 5));
			graficas.drawLine(
					segmentoHorizontal * (i + 2),
					segmentoVertical,
					segmentoHorizontal * (i + 2),
					segmentoVertical *(3+lines));
		}
		graficas.drawLine(
				segmentoHorizontal * 2,
				(segmentoVertical * 3/2),
				segmentoHorizontal * (2 + columns),
				(segmentoVertical * 3/2));
		for(int i = 0; i<lines; i++) {
			RegistroCalculo rc = registros.get(i);
			if(rc.getLlegada() < this.tiempoActual) {
				int anchoEspera = 0;
				if (rc.getLlegada()+ rc.getEspera() < this.tiempoActual) {
					anchoEspera = rc.getEspera();
				} else {
					anchoEspera = this.tiempoActual - rc.getLlegada();
				}
				// pintar tiempo espera
				graficas.setColor(Color.black);
				graficas.drawString(rc.getNombre(), segmentoHorizontal - 7, (i + 3) * segmentoVertical - 7);
				graficas.fillRect(
					(rc.getLlegada() + 2) * segmentoHorizontal,
					(i + 2) * segmentoVertical + 7,
					segmentoHorizontal * anchoEspera,
					segmentoVertical * 1/3);
				// pintar ejecucion
				if (rc.getLlegada()+ rc.getEspera() < this.tiempoActual) {
					int anchoRafaga = 0;
					if (rc.getLlegada()+ rc.getEspera()+rc.getRafada() < this.tiempoActual) {
						anchoRafaga = rc.getRafada();
					} else {
						anchoRafaga = this.tiempoActual - (rc.getLlegada()+ rc.getEspera());
					}
					graficas.setColor(colores[i % colores.length]);
					graficas.fillRect(
						(rc.getLlegada()+ rc.getEspera() + 2)*segmentoHorizontal,
						(i + 2)*segmentoVertical,
						segmentoHorizontal * anchoRafaga,
						segmentoVertical);
					graficas.fillRect(
						(rc.getLlegada()+ rc.getEspera() + 2)*segmentoHorizontal,
						(lines + 3) * segmentoVertical,
						segmentoHorizontal * anchoRafaga,
						segmentoVertical);
				}
				//verificacion terminacion
				if(i == lines - 1){
					this.finalizadoProcesos = (rc.getTranscurrido() <= this.tiempoActual);
				}
			}
		}
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
