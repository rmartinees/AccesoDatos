package primero;

public class Main {
	public static void main(String[] args) {

		JavaGeneralidades a = new JavaGeneralidades();
		System.out.println("Main: a-> " + a.toString());

		OtraClase.accessFinalVariable();

		JavaGeneralidadesExtend b = new JavaGeneralidadesExtend();
		System.out.println("Main: b-> " + b.toString());
	}
}