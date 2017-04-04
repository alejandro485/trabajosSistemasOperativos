package prueba;

import java.util.Random;
import java.util.Scanner;

import logica.FIFO;
import logica.Nodo;

public class PruebaFIFO {
	
	private static Scanner scan;

	public static void main(String[] arg) {
		scan = new Scanner(System.in);
		int contador = 0;
		FIFO fifo = new FIFO();
		while(true){
			Nodo nodo = fifo.getCabeza();
			while(nodo != null){
				System.out.println("===> "+nodo.getNombre()+": "+nodo.getUbicacion());
				nodo = nodo.getSig();
			}
			String in = scan.nextLine();
			if(in.equals("1")){
			    Random rand = new Random();
			    int randomNum = rand.nextInt(20);
			    for(int i = 0; i< randomNum; i++){
			    	fifo.agregar("p"+contador, rand.nextInt(200));
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
