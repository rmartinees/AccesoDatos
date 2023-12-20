package Ficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class E02cRandomAccessFileReadTXT {
	public static void main(String[] args) throws IOException {

		System.out.println("Lectura de un Fichero Acceso Directo grabado usando RandomAccessFile\n");
		File fr = new File("resources/ficheroBinFijo.dat");
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(fr, "r");
			int id, dept, pos;
			Double salario;
			char apellido[] = new char[10];

			pos = 0;
			for (;;) {
				raf.seek(pos);
				id = raf.readInt();
				for (int i = 0; i < apellido.length; ++i)
					apellido[i] = raf.readChar();

				String apelli2 = new String(apellido);
				dept = raf.readInt();
				salario = raf.readDouble();
				if (id > 0) {
					System.out.printf("ID %s, Apellido %s, Departamento %d, Salario %.2f %n", id, apelli2.trim(), dept,
							salario);
				}
				pos += 36;
				if (raf.getFilePointer() == raf.length())
					break;
			}
		} finally {
			raf.close();
		}
	}
}
