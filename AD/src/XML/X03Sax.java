package XML;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class X03Sax {
	public static void main(String[] args) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		XMLReader procesadorXML = saxParser.getXMLReader();
		GestionContenido gestor = new GestionContenido();
		procesadorXML.setContentHandler(gestor);
		InputSource fileXML = new InputSource("resources/alumnos.xml");
		procesadorXML.parse(fileXML);
	}
}

class GestionContenido extends DefaultHandler { //https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/org/xml/sax/helpers/DefaultHandler.html
	static int indent = 0;

	public GestionContenido() {
		super();
	}

	@Override
	public void startDocument() {
		System.out.println("Comienzo del Documento XML");
	}

	@Override
	public void endDocument() {
		System.out.println("Final del Documento XML");
	}

	@Override
	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		indentar(++indent);
		System.out.printf("Principio Elemento: %s %n", nombreC);
	}

	@Override
	public void endElement(String uri, String nombre, String nombreC) {
		indentar(indent--);
		System.out.printf("Fin Elemento: %s %n", nombreC);
	}

	@Override
	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String tmp = new String(ch, inicio, longitud).trim();
		if (tmp.length() > 0) {
			indentar(indent+1);
			System.out.printf("Contenido_elemento:%s%n", tmp);
		}
	}

	private void indentar(int j) {
		for (int i = j; i > 0; --i)
			System.out.printf("\t");
	}
}
