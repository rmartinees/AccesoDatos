package jaxb;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Ejemplo2_JAXB_Lectura {

    private static final String MIARCHIVO_XML = "resources/libreria.xml";

    public static void main(String[] args) throws JAXBException, IOException {
        // Creamos el contexto indicando la clase raíz
        JAXBContext context = JAXBContext.newInstance(Libreria.class);
        
        //Creamos el Unmarshaller
        Unmarshaller unmars = context.createUnmarshaller();
        //Se crea Unmarshaller en el contexto de la clase Libreria
        //Utilizamos el m�todo unmarshal, para obtener datos de un Reader     
        System.out.println("------------ Leo el XML ---------");
        Libreria libreria2 = (Libreria) unmars.unmarshal(new FileReader(MIARCHIVO_XML));

        //Recuperamos el array list y visualizamos
        ArrayList<Libro> lista = libreria2.getListaLibro();
        System.out.println("Nombre de librería: "+ libreria2.getNombre());
        System.out.println("Lugar de la librería: "+ libreria2.getLugar());
        System.out.println("Libros de la librería: ");

        for (Libro libro : lista)
            System.out.println("\tTítulo del libro: " + libro.getNombre() + " , autora: " + libro.getAutor());
        System.out.println("------------ XML Procesado --------");
    }
}