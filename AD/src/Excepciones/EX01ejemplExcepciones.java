package Excepciones;

public class EX01ejemplExcepciones {

	public static void main(String[] args) {
		String cad1 = "20", cad2 = "0", mensaje;
		int nume, denom, cociente;
		int[] arraynum = new int[4];

		try {
			arraynum[10] = 20; // sentencia que produce la excepción
			// Por aqui no pasa ya que petó la instruccion anterior
			nume = Integer.parseInt(cad1);
			denom = Integer.parseInt(cad2);
			cociente = nume / denom;
			mensaje = String.valueOf(cociente);
		} catch (NumberFormatException ex) {
			System.out.println("NumberFormatException");
			mensaje = "Caracteres no numéricos";
		} catch (ArithmeticException ex) {
			System.out.println("ArithmeticException");
			mensaje = "Division por cero";
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("ArrayIndexOutOfBoundsException");
			mensaje = "Fuera de rango en el array";
		} finally {
			System.out.println("El finally SE EJECUTA SIEMPRE");
		}
		System.out.println("El error detectado ha sido '" + mensaje + "'");
	}
}