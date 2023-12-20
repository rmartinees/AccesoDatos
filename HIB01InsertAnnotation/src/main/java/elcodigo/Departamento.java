package elcodigo;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
@Table
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    public Departamento() {
	}

	private String nombre;
    
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

	public Departamento(String nombre) {
		this.nombre = nombre;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	@OneToMany(mappedBy="departamento",cascade=CascadeType.PERSIST)
    private List<Empleado> empleados = new ArrayList<Empleado>();
     
     // Getters/Setters and Constructors omitted for the sake of brevity
    
}