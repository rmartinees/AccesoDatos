package Ficheros;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class E04EscribirSecuencialde10en10 {
	public static void main(String[] args) throws IOException {
		int i=0;
		String cbuf[] = {"HolasEstas\n",
				         "Noches son\n",
				         "Caloricas\n"
				        };

		System.out.println("Escritura de un Fichero Secuencial de 10 en 10\n");
		File fr = new File("resources/ficheroSecuencial2.txt");
		if (fr.exists()) {
			System.out.println("Fichero ya existe. Aborto operacion");
			return;
		}

		FileWriter frr = new FileWriter(fr);
		System.out.println("Empezando la Escritura");

		while (i<3)
			frr.write(cbuf[i++].toCharArray());

		System.out.println("Final. Cerrando fichero");
		frr.close();
	}
}
