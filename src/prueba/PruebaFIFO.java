package prueba;

import java.util.Random;
import java.util.Scanner;

import logica.fabricaPlanificadores.Generador;
import logica.planificacionDisco.Nodo;
import logica.planificacionDisco.Planificacion;

public class PruebaFIFO {
	
	private static Scanner scan;
	private static Generador generador;

	public static void main(String[] arg) {
		generador = new Generador();
		scan = new Scanner(System.in);
		int contador = 0;
		int anteriorLlegada = 0;
		Planificacion fifo = generador.getPlanificador("FIFO");
		while(true){
			Nodo cabeza = fifo.getCabeza();
			Nodo nodo = cabeza.getSig();
			int total = 0;
			while(nodo != cabeza){
				System.out.println("===> "+nodo.getNombre()+": "+nodo.getLlegada()+", "+nodo.getRafaa());
				nodo = nodo.getSig();
				total++;
			}
			System.out.println("Total de procesos en ejecucion: "+total);
			String in = scan.nextLine();
			if(in.equals("1")){
			    Random rand = new Random();
			    int randomNum = rand.nextInt(20);
			    for(int i = 0; i< randomNum; i++){
			    	fifo.agregar("p"+contador, rand.nextInt(200), anteriorLlegada);
			    	anteriorLlegada += rand.nextInt(5);
			    	contador++;
			    }
			} else if(in.equals("2")){
				fifo.remover();
			} else if(in.equals("3")){
				break;
			}
		}
	}
	
}
