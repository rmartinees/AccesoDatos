package Ficheros;

import java.io.FileOutputStream;
import java.io.IOException;

public class E06BinarioFileOutputStream {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("resources/FicheroBinario.bin");
			for (char i = 0; i < 100; ++i)
				fos.write(i);

		} catch (IOException e) {
			System.out.println("Imposible Abrir o Escribir a  Fichero");
			e.printStackTrace();
		} finally {
			fos.close();
		}

	}
}
