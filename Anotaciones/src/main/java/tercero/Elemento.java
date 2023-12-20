package tercero;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Elemento {
	@NotNull
	@Size(min = 2, max = 255, message = "El nombre, obligatorio. De 2 a 255 caracteres.")
	protected String nombre;
	@Future
	protected LocalDateTime finalPuja;

	@NotEmpty
	private String usuario;

	@Range(min = 18, max = 100, message = "La edad debe estar los 18 y 100 años")
	private int edad;
	
	public Elemento(String nombre, LocalDateTime finalPuja, String usuario, int edad) {
		this.nombre = nombre;
		this.finalPuja = finalPuja;
		this.usuario=usuario;
		this.edad=edad;
	}

	public Elemento() {
		this.nombre = "Prueba";
		this.finalPuja =  LocalDateTime.now().plusDays(4);
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getFinalPuja() {
		return finalPuja;
	}

	public void setFinalPuja(LocalDateTime finalPuja) {
		this.finalPuja = finalPuja;
	}

}
