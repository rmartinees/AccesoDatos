package elcodigo;

import jakarta.persistence.*;
 
@Entity
@Table
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    @ManyToOne
    private Departamento departamento;

	public Empleado() {
	}

	public Empleado(String nombre, Departamento departamento) {
		this.nombre = nombre;
		this.departamento = departamento;
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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

    // Getters/Setters and Constructors omitted for the sake of brevity
 

}
