package Vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Timer;

import Vista.Comando.PintarGantt;
import logica.calculos.RegistroCalculo;

public class CanvasGantt extends Canvas implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private int tiempoActual;
	private Timer timer;
	private PintarGantt gantt;
	
	public CanvasGantt() {
		this.setBackground(Color.white);
		this.gantt = new PintarGantt();
	}
	
	public void setRegistro(HashMap<String, RegistroCalculo> registros) {
		this.tiempoActual = 0;
		this.gantt.setRegistros(registros);
		this.timer = new Timer (Medidas.DIFERENCIAL_TIEMPO, this);
		this.timer.start();
	}
	
	public void update(Graphics g) {
		this.gantt.pintar(g);
	}
	
	public Recuadro getRecuadro(){
		return this.gantt.getRecuadro();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
		if(this.gantt.getColumnas() <= this.tiempoActual){
			this.timer.stop();
		}
		this.gantt.setTiempoActual(this.tiempoActual);
		this.tiempoActual++;
	}

}
