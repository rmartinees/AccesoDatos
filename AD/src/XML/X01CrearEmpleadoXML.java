package XML;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class X01CrearEmpleadoXML {
	public static void main(String[] args) throws IOException {
		File f = new File("resources/ficheroBinFijo.dat"); // Creado en E02bRandomAccessFileBINEmps
		RandomAccessFile fichOrigen = new RandomAccessFile(f, "r");

		int id, dep, posicion = 0;
		Double salario;
		char apellido[] = new char[10];

		System.out.println("Vamos a Convertir el fichero ficheroBinFijo.dat a XML ");

		// **************************************************************************
		// ************* CREAMOS LA DOM a partir de un fichero binario
		// **************************************************************************
		// Generamos un parser XML
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			// Generamos un Document con la interfaz DOMImplementation
			builder = factoria.newDocumentBuilder();
			DOMImplementation implementacion = builder.getDOMImplementation();
			Document documento = implementacion.createDocument(null, "Empleados", null);
			documento.setXmlVersion("1.0");

			for (;;) {
				fichOrigen.seek(posicion);

				// Lee los campos del registro actual
				id = fichOrigen.readInt();
	
				for (int i = 0; i < apellido.length; ++i)
					apellido[i] = fichOrigen.readChar();

				String apellidos = new String(apellido);
				dep = fichOrigen.readInt();
				salario = fichOrigen.readDouble();

				// Si tenemos numero de registro...
				if (id > 0) {
					// Creamos un elemento en la DOM a partir del mismo
					Element raiz = documento.createElement("empleado");
					documento.getDocumentElement().appendChild(raiz);
					CrearElemento("id", Integer.toString(id), raiz, documento);
					CrearElemento("apellido", apellidos.trim(), raiz, documento);
					CrearElemento("dep", Integer.toString(dep), raiz, documento);
					CrearElemento("salario", Double.toString(salario), raiz, documento);
				}
				if (fichOrigen.getFilePointer() == fichOrigen.length())
					break;
				posicion += 36; // Siguiente Registro
			}

			// **************************************************************************
			// *** Conversion de DOM a XML
			// **************************************************************************
			// *** A partir de la DOM en documento, la reconvertimos a XML con Transformer
			// **************************************************************************
			DOMSource fuente = new DOMSource(documento);
			StreamResult result = new StreamResult(new java.io.File("resources/Empleados.xml"));
			Transformer transfor;
			try {
				transfor = TransformerFactory.newInstance().newTransformer();
				transfor.transform(fuente, result);
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} finally {
			fichOrigen.close();
		}
	}

	static void CrearElemento(String datoEmple, String valor, Element raiz, Document documento) {
		Element elem = documento.createElement(datoEmple);
		Text text = documento.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}
}