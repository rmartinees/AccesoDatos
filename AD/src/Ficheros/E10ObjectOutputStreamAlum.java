package Ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class E10ObjectOutputStreamAlum extends ObjectOutputStream {

	public E10ObjectOutputStreamAlum(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		// no escribas la cabecera!!
		reset();
	}

	public static void main(String[] args) throws IOException {
		ObjectOutputStream oos;
		File fitx = new File("resources/ficheroObjectStreamAlumnos.bin");

		if (fitx.exists()) {
			oos = new E10ObjectOutputStreamAlum(new FileOutputStream(fitx, true));
		} else {
			oos = new ObjectOutputStream(new FileOutputStream(fitx));
		}

		oos.writeObject(new Alumno("Josefa", 834, 'M'));
		oos.writeObject(new Alumno("Maria", 9134, 'M'));
		oos.close();

		ObjectInputStream ids = new ObjectInputStream(new FileInputStream("resources/ficheroObjectStreamAlumnos.bin"));
		try {
			while (true) {
				Alumno b = (Alumno) ids.readObject();
				System.out.println(b.toString());
			}
		} catch (EOFException eo) {
			System.out.println("Fichero Finalizado");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			ids.close();
		}
	}
}
