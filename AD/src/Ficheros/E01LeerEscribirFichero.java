package Ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class E01LeerEscribirFichero {
	public static void main(String[] args) throws IOException {
		String linea;
		BufferedReader in = null;
		BufferedWriter out = null;

		File fr = new File("resources/ficheroSecuencial.txt");
		File fw = new File("resources/ficheroSecuencial.tmp");

		System.out.println("Conversion de un Fichero Secuencial usando BufferedReader/Writer\n");

		//Leyamos el registro a Borrar
		Scanner leer = new Scanner(System.in); // Create a Scanner object
		System.out.print("Num Registro a Borrar: ");
		int filaBorrar = leer.nextInt();
		int numFil = 1;
		leer.close();

		try {
			in = new BufferedReader(new FileReader(fr));
			out = new BufferedWriter(new FileWriter(fw));
			System.out.println("Empezando la Conversión");

			while ((linea = in.readLine()) != null) {
				if (filaBorrar != numFil++) {
					linea = linea.replace("XXX", "2-AMT");
					out.write(linea + '\n');
				}
			}
			if (filaBorrar >=numFil) System.out.println("Error Fila " + filaBorrar+ " que se debia borrar NO existe");

		} catch (IOException e) {
			throw e;
		} finally {
			System.out.println("Cerrando los Ficheros");
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Renombremos los ficheros
		System.out.println("Renombrando...");
		fr.delete();
		fw.renameTo(fr);
		System.out.println("\nConversion finalizada");
	}
}