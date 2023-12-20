package Ficheros;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class E03LeerSecuencialde20en20 {
	public static void main(String[] args) throws IOException {

		char cbuf[] = new char[20];

		File fr = new File("resources/ficheroSecuencial.txt");
		System.out.println("Lectura de un Fichero Secuencial de 20 en 20\n");

		FileReader frr = new FileReader(fr);
		System.out.println("Empezando la Lectura:>>");

		while (frr.read(cbuf) != -1)
			System.out.print(cbuf);

		System.out.println("<< Final. Cerrando fichero");
		frr.close();
	}
}
