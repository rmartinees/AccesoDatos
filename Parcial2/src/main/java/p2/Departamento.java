package p2;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table
public class Departamento {
	@Id
	String nombre; 
	
	@OneToMany(mappedBy = "dept", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<DepartamentoEmpleado> dptoEmp = new ArrayList<DepartamentoEmpleado>();

	public Departamento() {
	}

	public Departamento(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DepartamentoEmpleado> getHijos() {
		return dptoEmp;
	}

	public void setHijos(List<DepartamentoEmpleado> hijos) {
		this.dptoEmp = hijos;
	}

	@Override
	public String toString() {
		String h = "{nombre='" + nombre + "\' ";
		for (int j = 0; j < dptoEmp.size(); ++j)
			h += "[(" + (j+1) + ") " + dptoEmp.get(j).getEmp().getNombre() + "]";

		return h + '}';
	}
}