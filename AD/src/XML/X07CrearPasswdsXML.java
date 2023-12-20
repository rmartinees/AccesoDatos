package XML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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

public class X07CrearPasswdsXML {
	public static void main(String[] args) throws IOException {
		File f = new File("C:/Users/rmart/Downloads/passwd.txt");
		BufferedReader fichOrigen = new BufferedReader(new FileReader(f));

		String lectura;
		String[] registro;

		System.out.println("Vamos a Convertir el fichero passwd.txt a XML ");

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
			Document documento = implementacion.createDocument(null, "TablaUsuarios", null);
			documento.setXmlVersion("1.0");
			lectura = fichOrigen.readLine();
			for (;;) {

				// Lee los campos del registro actual
				lectura = fichOrigen.readLine();
				if (lectura == null)
					break;
				registro = lectura.split(":");
				// Creamos un elemento en la DOM a partir del mismo
				Element raiz = documento.createElement("Usuario");
				documento.getDocumentElement().appendChild(raiz);
				CrearElemento("username", registro[0], raiz, documento);
				CrearElemento("uid", registro[2], raiz, documento);

			}

			// **************************************************************************
			// *** Conversion de DOM a XML
			// **************************************************************************
			// *** A partir de la DOM en documento, la reconvertimos a XML con Transformer
			// **************************************************************************
			DOMSource fuente = new DOMSource(documento);
			StreamResult result = new StreamResult(new java.io.File("resources/passwd.xml"));
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