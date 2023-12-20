package Ficheros;

import java.io.FileInputStream;
import java.io.IOException;

public class E07BinarioFileInputStream {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = null;
		byte b[]=new byte[1000];
		try {
			fis = new FileInputStream("resources/FicheroBinario.bin");
			fis.read(b);
			for (byte element : b)
				System.out.println(element);
		} catch (IOException e) {
			System.out.println("Imposible Abrir o Leer Fichero");
			e.printStackTrace();
		} finally {
			fis.close();
		}
	}
}
