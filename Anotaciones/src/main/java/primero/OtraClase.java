package primero;

class OtraClase {
	public static void accessFinalVariable() {
		JavaGeneralidades obj = new JavaGeneralidades();
		System.out.println("OtraClase: finalVariable: " + obj.finalVariable);
		
		JavaGeneralidadesExtend c = new JavaGeneralidadesExtend();
		c.protectedVariable = 99999;
		System.out.println("Main: c-> " + c.toString());
		
		//accedo a protectedVariable porque estoy en el mismo package
		//Cambia el nombre del package a pruebas1 y mira qué pasa
		
	}
}