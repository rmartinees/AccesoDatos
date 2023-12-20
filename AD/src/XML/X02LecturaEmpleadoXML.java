package XML;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class X02LecturaEmpleadoXML {

	public static void main(String[] args) {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factoria.newDocumentBuilder();  //Creamos una instancia 
			Document documento = builder.parse(new File("resources/Empleados.xml")); //Y cargamos el documento xml
			documento.getDocumentElement().normalize();  //Pone todos los nodos texto en documento
			System.out.printf("Elemento Raiz %s \n", documento.getDocumentElement().getNodeName());

			// Crea una lista con sólo los nodos empleados
			NodeList empleados = documento.getElementsByTagName("empleado");
			System.out.printf("Nodos empleado a recorrer: %d \n", empleados.getLength());

			// Recorre la lista
			for (int i = 0; i < empleados.getLength(); ++i) {
				Node emple = empleados.item(i);
				if (emple.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) emple;
					System.out.printf("ID = %s\n", elemento.getElementsByTagName("id").item(0).getTextContent());
					System.out.printf(" * Apellido = %s\n",
							elemento.getElementsByTagName("apellido").item(0).getTextContent());
					System.out.printf(" * Departamento  = %s\n",
							elemento.getElementsByTagName("dep").item(0).getTextContent());
					System.out.printf(" * Salario = %s\n",
							elemento.getElementsByTagName("salario").item(0).getTextContent());
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}