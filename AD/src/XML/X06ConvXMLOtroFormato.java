package XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class X06ConvXMLOtroFormato {
	public static void main(String[] args) throws IOException {
		String hojaEstilo = "resources/AlumnosPlantilla.xsl";
		String datosAlumnos = "resources/alumnos.xml";
		File pagHTML = new File("resources/mipagina.html");

		FileOutputStream os = new FileOutputStream(pagHTML);
		StreamSource estilos = new StreamSource(hojaEstilo);
		StreamSource datos = new StreamSource(datosAlumnos);

		StreamResult resultado = new StreamResult(os);

		try {
			Transformer transf = TransformerFactory.newInstance().newTransformer(estilos);
			transf.transform(datos, resultado);
		} catch (Exception e) {
			System.err.println("Error " + e);
		} finally {
			os.close();
		}
	}
}