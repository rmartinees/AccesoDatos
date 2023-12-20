package XML;

import java.util.ArrayList;
import java.util.List;

import Ficheros.Alumno;

public class ListaAlumnos {
	private List<Alumno> lista = new ArrayList<>();

	public ListaAlumnos() {
	}

	public void add(Alumno alu) {
		lista.add(alu);
	}

	public List<Alumno> getListaAlumnos() {
		return lista;
	}
}
