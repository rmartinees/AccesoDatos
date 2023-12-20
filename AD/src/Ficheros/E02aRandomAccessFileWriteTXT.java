 package Ficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class E02aRandomAccessFileWriteTXT {
	public static void main(String[] args) throws IOException {
		System.out.println("Mantenimiento de un Fichero Acceso Directo usando RandomAccessFile\n");
		Scanner leer = new Scanner(System.in); // Create a Scanner object

		File fr = new File("resources/ficheroFijo.txt");
		int long_reg = 25 + 1; // C1-Valido:1 C2-Nombre:10 C3-Apellido: 10 C4-Edad:3 C5-Sexo;1
		int campos[] = new int[] { 0, 1, 11, 21, 24 };
		String valores[] = new String[] { "X", "NOMBRE", "APELLIDO", "666", "-" };

		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(fr, "rw");
			int numReg = (int) raf.length() / long_reg;

			while (true) {
				int reg = leer(leer, "Registro a Modificar", numReg);
				if (reg == 0)
					break;
				int campo = leer(leer, "Campo a Modificar", 5);
				// Me Posiciono y escribo
				System.out.println("Seek=" + (reg - 1) * long_reg + " "+campos[campo - 1]);
				raf.seek((reg - 1) * long_reg + campos[campo - 1]);
				raf.write(valores[campo - 1].getBytes());
			}
		} catch (IOException e) {
			System.out.println("Imposible acceder al fichero");
			//Como es de escritura siempre podremos!! ¿Qué hacer por si no está el fichero que esperaba encontrar?
		} finally {
			System.out.println("Cerrando...");
			raf.close();
			leer.close();
		}
		System.out.println("\nFin Gestion Fichero Fijo");
	}

	public static int leer(Scanner leer, String mens, int valido) {
		int valor;
		do {
			System.out.print(mens + " (max=" + valido + "): ");
			valor = leer.nextInt();
			if (valor > valido)
				System.out.println("Valor [" + valor + "] no valido");
		} while (valor > valido);
		return valor;
	}

}
