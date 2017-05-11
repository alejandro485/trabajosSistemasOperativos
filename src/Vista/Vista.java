package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import logica.calculos.Calculos;
import logica.calculos.RegistroCalculo;
import logica.fabricaPlanificadores.Generador;

public class Vista extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private Calculos calculos;
	private Generador generador;

	private JPanel contentPane;
	private JButton btnAgregar;
	private JButton btnLimpiar;
	
	private JButton btnAbajo;
	private JButton btnArriba;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	
	private CanvasGantt canvas;
	private JTable tbCalculos;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Vista();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Vista() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addKeyListener(this);

		btnAgregar = new JButton("Agregar y atender");
		btnAgregar.setBounds(20, 20, 250, 30);
		btnAgregar.setActionCommand("ag");
		btnAgregar.addActionListener(this);
		contentPane.add(btnAgregar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(20, 70, 250, 30);
		btnLimpiar.setActionCommand("lm");
		btnLimpiar.addActionListener(this);
		contentPane.add(btnLimpiar);
		
		btnAbajo = new JButton("v");
		btnAbajo.setBounds(290, 570, 60, 30);
		btnAbajo.setActionCommand("ab");
		btnAbajo.addActionListener(this);
		contentPane.add(btnAbajo);
		
		btnArriba = new JButton("^");
		btnArriba.setBounds(370, 570, 60, 30);
		btnArriba.setActionCommand("ar");
		btnArriba.addActionListener(this);
		contentPane.add(btnArriba);
		
		btnDerecha = new JButton(">");
		btnDerecha.setBounds(450, 570, 60, 30);
		btnDerecha.setActionCommand("de");
		btnDerecha.addActionListener(this);
		contentPane.add(btnDerecha);
		
		btnIzquierda = new JButton("<");
		btnIzquierda.setBounds(530, 570, 60, 30);
		btnIzquierda.setActionCommand("iz");
		btnIzquierda.addActionListener(this);
		contentPane.add(btnIzquierda);
		
		tbCalculos = new JTable();
		tbCalculos.setBounds(20, 120, 250, 480);
		contentPane.add(tbCalculos);

		canvas = new CanvasGantt();
		canvas.setBounds(290, 20, 690, 540);
		contentPane.add(canvas);
		
		calculos = new Calculos();
		generador = new Generador();
		calculos.setPlanificador(generador.getPlanificador("SJFE"));
		this.setFocusable(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("ag")){
			calculos.agregar();
			calculos.calcular();
			canvas.setRegistro(calculos.getRegistros());
			DefaultTableModel model=new DefaultTableModel();
			model.addColumn("nombre");
			model.addColumn("llegada");
			model.addColumn("rafaga");
			model.addColumn("final");
			model.addColumn("espera");
			model.addColumn("tras");
			model.addRow(new Object[]{"Nombre","Llegada" ,"Rafaga" ,"Finalizacion" ,"Espera", "Transcurso"});
			for(int i = 0 ; i < calculos.getRegistros().size(); i++) {
				RegistroCalculo rc = calculos.getRegistros().get("p"+i);
				model.addRow(new Object[]
					{
						rc.getNombre(),
						rc.getLlegada(),
						rc.getRafada(),
						rc.getFinalizacion(),
						rc.getEspera(),
						rc.getTranscurrido()
					});
			}
			tbCalculos.setModel(model);
		} else if (e.getActionCommand().equals("lm")) {
			calculos.limpiar();
			DefaultTableModel model=new DefaultTableModel();
			tbCalculos.setModel(model);
			canvas.repaint();
		} else if (e.getActionCommand().equals("ab")) {
			canvas.cambioSeccionImagen(2);
		} else if (e.getActionCommand().equals("ar")) {
			canvas.cambioSeccionImagen(1);
		} else if (e.getActionCommand().equals("de")) {
			canvas.cambioSeccionImagen(4);
		} else if (e.getActionCommand().equals("iz")) {
			canvas.cambioSeccionImagen(3);
		}
		this.setFocusable(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	            canvas.cambioSeccionImagen(1);
	            break;
	        case KeyEvent.VK_DOWN:
	            canvas.cambioSeccionImagen(2);
	            break;
	        case KeyEvent.VK_LEFT:
	            canvas.cambioSeccionImagen(3);
	            break;
	        case KeyEvent.VK_RIGHT :
	            canvas.cambioSeccionImagen(4);
	            break;
	     }
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}