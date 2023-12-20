package primero;

public class JavaGeneralidades {

	final int finalVariable;
	private int privateVariable;
	protected int protectedVariable;

	public JavaGeneralidades() {
		privateVariable = 500;
		protectedVariable = 50;
		finalVariable = 5; // Sólo se puede asignar una vez
		// finalVariable = 55; // Error: se debe asignar sólo una vez.
	}

	@Override
	public String toString() {
		return ("[\n  unaVariable=" + privateVariable + "\n  otraVariable=" + protectedVariable + "\n  finalVariable=" + finalVariable
				+ "\n]\n");
	}
	// Antes de seguir crea los getters y setters
	// Fíjate que hay un setter que no crea: ¿Por qué?

	public int getUnaVariable() {
		return privateVariable;
	}

	public void setUnaVariable(int unaVariable) {
		this.privateVariable = unaVariable;
	}

	public int getOtraVariable() {
		return protectedVariable;
	}

	public void setOtraVariable(int otraVariable) {
		this.protectedVariable = otraVariable;
	}

	public int getFinalVariable() {
		return finalVariable;
	}

}