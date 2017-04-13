package Vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import logica.calculos.RegistroCalculo;

public class CanvasGantt extends Canvas{
	
	private static final long serialVersionUID = 1L;
	
	private Image imagen;
	private Graphics graficas;
	private int segmentoHorizontal;
	private int segmentoVertical;
	private ArrayList<RegistroCalculo> registros;
	private int wt;
	private int ht;
	private Color[] colores;
	
	public CanvasGantt() {
		this.segmentoHorizontal = 10;
		this.segmentoVertical = 20;
		this.wt = 0;
		this.ht = 0;
		this.registros = new ArrayList<>();
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
		this.repaint();
	}
	
	public void update(Graphics g) {
		pintarGantt();
		g.drawImage(imagen, 0, 0, this);
		graficas.clearRect(0, 0, wt, ht);
	}
	
	private void pintarGantt() {
		int lines = registros.size();
		int columns = 0;
		if(lines > 0) {
			columns = registros.get(registros.size() - 1).getTranscurrido();
		} else {
			columns = 0;
		}
		int width = (columns + 4) * segmentoHorizontal;
		int height = (lines + 5) * segmentoVertical;
		if(this.wt < width) {
			this.wt = width;
		}
		if(this.ht < height){
			this.ht = height;
		}
		imagen = createImage(this.wt,this.ht);
		graficas = imagen.getGraphics();
		graficas.setColor(Color.white);
		graficas.fillRect(0,0 , this.wt, this.ht);
		graficas.setColor(Color.black);
		graficas.drawLine(
				segmentoHorizontal * 2,
				(segmentoVertical * 3/2),
				segmentoHorizontal * (2 + columns),
				(segmentoVertical * 3/2));
		for(int i = 0; i<=columns; i++){
			//graficas.drawString(i+"", segmentoHorizontal * (i + 2), segmentoVertical);
			graficas.drawLine(
					segmentoHorizontal * (i + 2),
					segmentoVertical,
					segmentoHorizontal * (i + 2),
					segmentoVertical *(3+lines));
		}
		for(int i = 0; i<lines; i++) {
			graficas.setColor(colores[i % colores.length]);
			RegistroCalculo rc = registros.get(i);
			graficas.fillRect(
				(rc.getLlegada()+ rc.getEspera() + 2)*segmentoHorizontal,
				(i + 2)*segmentoVertical,
				segmentoHorizontal * rc.getRafada(),
				segmentoVertical);
			graficas.setColor(Color.black);
			graficas.drawString(rc.getNombre(), segmentoHorizontal - 7, (i + 3) * segmentoVertical - 7);
			graficas.fillRect(
				(rc.getLlegada() + 2) * segmentoHorizontal,
				(i + 2) * segmentoVertical + 7,
				segmentoHorizontal * rc.getEspera(),
				segmentoVertical * 1/3);
		}
	}

}
