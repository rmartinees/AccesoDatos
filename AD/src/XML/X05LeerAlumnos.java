package XML;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Ficheros.Alumno;

public class X05LeerAlumnos { // Serializacion de objetos a XML

	public static void main(String[] args) throws IOException {
		XStream xstream = new XStream();

		// Create and apply a  custom security framework to allow your custom classes
        xstream.addPermission(new AnyTypePermission());

		xstream.alias("ListaAlumnosIES", ListaAlumnos.class);
		xstream.alias("DatosAlumnos", Alumno.class);
		xstream.addImplicitCollection(ListaAlumnos.class, "lista");
		//Como Cambiamos el nombre al escribir, tenemos que seguir mapeando en el XML... el campo "SexoGenero"  de la clase Alumno, a la variable "sexo"
		//Si lo usaste al escribir, lo has de usar al leer. Prueba a usarlo al escribir, y no al leer, o viceversa
		xstream.aliasField("SexoGenero", Alumno.class, "sexo");
		

		ListaAlumnos listTodosAlumns = (ListaAlumnos) xstream.fromXML(new FileInputStream("resources/Aalumnos.xml"));
		System.out.println("Leyendo fichero XML....");
		System.out.println(" Numero de Alumnos: " + listTodosAlumns.getListaAlumnos().size());
		List<Alumno> listAlumns = new ArrayList<>();

		listAlumns = listTodosAlumns.getListaAlumnos();
		for (Alumno a : listAlumns) {
			System.out.printf("  Nombre: %s, edad: %d, Sexo: %c %n", a.getNombre(), a.getEdad(), a.getSexo());
		}
		System.out.println("Fin del listado");
	}
}
