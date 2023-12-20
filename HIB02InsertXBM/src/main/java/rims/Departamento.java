package rims;

public class Departamento {

    private Long id;
	private String nombre;
    
    public Departamento() {
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

	public Departamento(String nombre) {
		this.nombre = nombre;
	}    
}