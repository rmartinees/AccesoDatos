package Ficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class E020LecturaRandom {

	public static void main(String[] args) throws IOException {
		RandomAccessFile r = null;
		byte lectura12[] = new byte[4];
		File fr = new File("resources/ficheroFijo.dat");

		System.out.println("PruebaRandom lectura 4 caracteres");
		r = new RandomAccessFile(fr, "r");

		int numRegs = (int) (r.length() / 16);
		System.out.println("LA longitud es "+numRegs+" ");
		r.seek(16+12);
		r.read(lectura12);

		System.out.println("Los siguientes 4 bytes son: >"+ new String(lectura12) +"<");

		r.close();
	}

}
