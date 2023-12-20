package primero;

public class JavaGeneralidadesExtend extends JavaGeneralidades {
	public void modifico() {
		// super.finalVariable = 7; //ERROR
		setUnaVariable(100);
		super.protectedVariable = 1000;
		// Puedo modificar la protected sin pasar por los setters!!!
		// Pero sólo dentro de la clase que la extiende!!
	}
}
