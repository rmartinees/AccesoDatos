package XML;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.thoughtworks.xstream.XStream;

import Ficheros.Alumno;

public class X04EscribirAlumnos { // Serializacion de objetos a XML

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File fichero = new File("resources/ficheroObjectStreamAlumnos.bin");
		FileInputStream filein = new FileInputStream(fichero);
		ObjectInputStream dataIS = new ObjectInputStream(filein);

		System.out.println("Comienza el proceso de Serialización del objeto Alumno a XML...");

		// Creamos un objeto Lista de Alumnos
		ListaAlumnos listAlumns = new ListaAlumnos();

		try {
			while (true) { // lectura del fichero
				Alumno alumno = (Alumno) dataIS.readObject();
				listAlumns.add(alumno); // añadir alumno a la lista
			}
		} catch (EOFException eo) {
		}
		dataIS.close(); // cerrar stream de entrada

		try {
			XStream xstream = new XStream();

			// Asignar nombre a las etiquetas XML (y que no coja por defecto el nombre del campo)
			//----> Comenta y descomenta estos campos para asi ver qué hacen
			xstream.alias("ListaAlumnosIES", ListaAlumnos.class);
			xstream.alias("DatosAlumnos", Alumno.class);
			
			//Cambiamos el nombre del campo "sexo", de la clase Alumno, a "SexoGenero" en el XML
			xstream.aliasField("SexoGenero", Alumno.class, "sexo");
			
			// quitar etiqueta lista (atributo de la clase ListaAlumnos)
			xstream.addImplicitCollection(ListaAlumnos.class, "lista");

			// Insertar los objetos en el XML
			String datosAlumnos = "resources/Aalumnos.xml";
			xstream.toXML(listAlumns, new FileOutputStream(datosAlumnos));
			System.out.println("\nFIN: Creado fichero " + datosAlumnos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
