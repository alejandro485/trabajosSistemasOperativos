package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import logica.calculos.Calculos;
import logica.calculos.RegistroCalculo;
import logica.fabricaPlanificadores.Generador;

public class Vista extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Calculos calculos;
	private Generador generador;

	private JPanel contentPane;
	private JButton btnGraficar;
	private JButton btnAgregar;
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
		setBounds(100, 100, 1200, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAgregar = new JButton("Apilar");
		btnAgregar.setBounds(20, 20, 117, 25);
		btnAgregar.setActionCommand("ag");
		btnAgregar.addActionListener(this);
		contentPane.add(btnAgregar);

		btnGraficar = new JButton("Atender");
		btnGraficar.setBounds(157, 20, 117, 25);
		btnGraficar.setActionCommand("gr");
		btnGraficar.addActionListener(this);
		contentPane.add(btnGraficar);
		
		tbCalculos = new JTable();
		tbCalculos.setBounds(20, 65, 254, 485);
		contentPane.add(tbCalculos);

		canvas = new CanvasGantt();
		canvas.setBounds(294, 20, 1000, 620);
		contentPane.add(canvas);
		
		calculos = new Calculos();
		generador = new Generador();
		calculos.setPlanificador(generador.getPlanificador("FIFO"));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("ag")){
			calculos.agregar();
		} else if(e.getActionCommand().equals("gr")) {
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
				RegistroCalculo rc = calculos.getRegistros().get(i);
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
		}
	}
}