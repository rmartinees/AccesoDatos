package Ficheros;

import java.io.Serializable;
import java.util.Date;

public class Alumno implements Serializable {
	private String nombre;
	private int edad;
	private char sexo;
    private Date fecha;

	public Alumno() {
		nombre = "";
		edad = 100;
		sexo = '-';
	}

	public Alumno(String nombre, int edad, char sexo) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.fecha = new Date();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "<" + nombre + " [" + edad + "] " + sexo +  "> "+fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
