package Ficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class E020EscrituraRandom {

	public static void main(String[] args) throws IOException {
		RandomAccessFile r = null;

		File fr = new File("resources/ficheroFijo.dat");

		r = new RandomAccessFile(fr, "rw");

		int dept[] = { 3, 4, 5 };
		double salarios[] = { 1.1, 2.2, 3.3 };
		String nombre[] = { "aaaa", "zzzz", "cccc" };

		for (int i = 0; i < dept.length; ++i) {
			r.writeInt(dept[i]);
			r.writeDouble(salarios[i]);
			r.writeBytes(nombre[i]);
		}
		r.close();
	}
}