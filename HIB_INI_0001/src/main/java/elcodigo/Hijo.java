package elcodigo;

import jakarta.persistence.*;

@Entity
@Table
public class Hijo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@ManyToOne
	private Padre padre;
int burjula;

	public Hijo() {
	}

	public Hijo(String nombre, Padre padre) {
		this.nombre = nombre;
		this.padre = padre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Padre getPadre() {
		return padre;
	}

	public void setPadre(Padre padre) {
		this.padre = padre;
	}

	@Override
	public String toString() {
		String h = "{" + "id=" + id + ", nombre='" + nombre + "\'  ";
		
		h += "[" + padre.getNombre() + "]";

		return h + '}';
	}

}