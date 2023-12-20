package Excepciones;

public class EX02ejemplExcepciones {

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
		}catch (Exception ex) {
			System.err.println("toString ==> "+ex.toString());
			System.err.println("getMessage ==> "+ex.getMessage());
			mensaje=ex.getLocalizedMessage();
		} finally {
			System.err.println("El finally SE EJECUTA SIEMPRE");
		}
		System.err.println("El error detectado ha sido '" + mensaje + "'");
	}
}