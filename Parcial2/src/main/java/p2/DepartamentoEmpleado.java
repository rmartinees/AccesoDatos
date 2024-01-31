package p2;
import jakarta.persistence.*;

@Entity
@Table
public class DepartamentoEmpleado { 
	@Id
	@ManyToOne	
	Departamento dept;
	@Id
	@ManyToOne
	Empleado emp;

	public DepartamentoEmpleado() {
	
	}
	public DepartamentoEmpleado(Departamento dept, Empleado emp) {
	   this.dept=dept;
	   this.emp=emp;
	}
	
	 public Empleado getEmp() {
		return emp;
	}
	public void setEmp(Empleado emp) {
		this.emp = emp;
	}
	public Departamento getDept() {
		return dept;
	}
	public void setDept(Departamento dept) {
		this.dept = dept;
	}
}