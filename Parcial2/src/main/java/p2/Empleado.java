package p2;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table
public class Empleado { 
	@Id 
	String nombre; 
	@OneToMany (mappedBy="emp",fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	private List<DepartamentoEmpleado> hijos = new ArrayList<DepartamentoEmpleado>();
	
	public Empleado() {
	}
	public Empleado(String nombre) {
		 this.nombre= nombre; 
	}
	 
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<DepartamentoEmpleado> getHijos() {
		return hijos;
	}
	public void setHijos(List<DepartamentoEmpleado> hijos) {
		this.hijos = hijos;
	}

}