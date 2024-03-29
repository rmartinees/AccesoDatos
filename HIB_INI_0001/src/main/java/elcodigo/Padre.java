package elcodigo;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table
public class Padre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;

	//@OneToMany
	@OneToMany(mappedBy="padre",fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	private List<Hijo> hijos = new ArrayList<Hijo>();

	public List<Hijo> getHijos() {
		return hijos;
	}

	public void setHijos(List<Hijo> hijos) {
		this.hijos = hijos;
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

	public Padre() {
	}

	public Padre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		String h = "{" + "id=" + id + ", nombre='" + nombre + '\'';
		if (this.getHijos().isEmpty()) h+=" [Sin hijos]";
		for (Hijo hij : this.getHijos())
			h += "\n\t" + hij.getNombre() + "[" + hij.getId() + "]";
		
		return h + '}';
	}

}