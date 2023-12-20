package Ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class E09ObjectOutputStreamAlum {
	public static void main(String[] args) throws IOException {
		ObjectOutputStream oos;
		File fitx = new File("resources/ficheroObjectStreamAlumnos.bin");
	
		oos = new ObjectOutputStream(new FileOutputStream(fitx));  //<-- Despues de ejecutar una vez, añade , true al FileOS, a ver qué pasa
		oos.writeObject(new Alumno("Pedro", 34, 'H'));
		oos.writeObject(new Alumno("fedra", 134, 'M'));
		oos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/ficheroObjectStreamAlumnos.bin"));
		try {
			while (true) {
				Alumno b = (Alumno) ois.readObject();
				System.out.println(b.toString());
			}
		} catch (EOFException eo) {
			System.out.println("Fichero Finalizado");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			ois.close();
		}
	}
}
