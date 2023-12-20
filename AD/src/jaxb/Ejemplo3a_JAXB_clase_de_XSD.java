package jaxb;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

import generated.DatosArtic;
import generated.ObjectFactory;
import generated.Ventas;
import generated.VentasType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;


// CREA EL FICHERO ventasarticulo.xml

public class Ejemplo3a_JAXB_clase_de_XSD {

	public static void main(String[] args) {
		crearnuevoventasxml();
	}

	public static void crearnuevoventasxml() {
		// Crear el objeto DatosArtic
		DatosArtic articulo = new DatosArtic();
		articulo.setCodigo("Arti 1");
		articulo.setDenominacion("Bicicleta Plegable");
		BigInteger stv = BigInteger.valueOf(10);
		BigDecimal pvv = BigDecimal.valueOf(200);
		articulo.setPrecio(pvv);
		articulo.setStock(stv);

		// Creamos el objeto Ventas
		Ventas ventas = new Ventas();
		// Creo la primera venta y la añado a ventas
		Ventas.Venta ven = new Ventas.Venta();
		ven.setNombrecliente("Alicia Ramos");
		ven.setNumventa(BigInteger.valueOf(1));
		ven.setUnidades(2);
		ven.setFecha("10-02-2016");
		ventas.getVenta().add(ven);
		// Creo la segunda venta y la añado a ventas
		ven = new Ventas.Venta();
		ven.setNombrecliente("Dori Martín");
		ven.setNumventa(BigInteger.valueOf(2));
		ven.setUnidades(1);
		ven.setFecha("21-02-2016");
		ventas.getVenta().add(ven);

		// Creo un VentasType y asigno los datos
		VentasType miventaarticulo = new VentasType();
		miventaarticulo.setArticulo(articulo);
		miventaarticulo.setVentas(ventas);

		// Creo el ObjectFactory
		ObjectFactory miarticulo = new ObjectFactory();
		// Creo El JAXBElement lo obtenemos del ObjectFactory y del VentasType
		JAXBElement<VentasType> element = miarticulo.createVentasarticulos(miventaarticulo);
		// Creo el contexto y el Marshaller
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			String fiche = "resources/ventasarticulos.xml";
			m.marshal(element, new FileOutputStream(fiche));
			System.out.println("Venta creada. ");
			// Visualizamos por consola
			m.marshal(element, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}