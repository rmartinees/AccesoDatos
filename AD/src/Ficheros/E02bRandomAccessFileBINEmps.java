package Ficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class E02bRandomAccessFileBINEmps {
	public static void main(String[] args) throws IOException {

		String apellido[] = { "Martínez", "Abedúl", "Ayala", "Pérez" };
		int dep[] = { 11, 22, 33, 44 };
		Double salario[] = { 18000.33123, 22000.456, 17500.129, 23000.34 };
		StringBuffer buffer = null;

		int n = apellido.length;

		System.out.println("Creación de un Fichero Acceso Directo usando RandomAccessFile y StringBuffer\n");
		File fr = new File("resources/ficheroBinFijo.dat");
		RandomAccessFile raf = new RandomAccessFile(fr, "rw");

		for (int i = 0; i < n; ++i) {
			raf.writeInt(i + 1);
			buffer = new StringBuffer(apellido[i]);
			buffer.setLength(10);
			raf.writeChars(buffer.toString());
			raf.writeInt(dep[i]);
			raf.writeDouble(salario[i]);
		}
		raf.close();
	}
}
