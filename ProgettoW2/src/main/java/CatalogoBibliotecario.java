import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CatalogoBibliotecario{
    private List<ElementoCatalogo> catalogo;
    public CatalogoBibliotecario() {
        catalogo = new ArrayList<>();
    }

    public void aggiungiLibro(Libro libro) {
        catalogo.add(libro);
    }

    public void aggiungiRivista(Rivista rivista) {
        catalogo.add(rivista);
    }

    public void rimuoviElemento(String codiceISBN) {
        catalogo.removeIf(e -> e.getCodiceISBN().equals(codiceISBN));
    }

    public Optional<ElementoCatalogo> cercaPerISBN(String codiceISBN) {
        return catalogo.stream()
                .filter(e -> e.getCodiceISBN().equals(codiceISBN))
                .findFirst();
    }

    public List<ElementoCatalogo> cercaPerAnnoPubblicazione(int annoPubblicazione) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == annoPubblicazione)
                .toList();
    }

    public List<ElementoCatalogo> cercaPerAutore(String autore) {
        String lowercaseAutore = autore.toLowerCase();
        List<ElementoCatalogo> filtered = catalogo.stream()
                .filter(e -> e instanceof Libro)
                .peek(e -> System.out.println("Libro: " + ((Libro) e).getAutore()))
                .filter(e -> ((Libro) e).getAutore().toLowerCase().equals(lowercaseAutore))
                .toList();
        System.out.println("Found results: " + filtered);
        return null;
    }





    public void salvaSuDisco(String fileName) throws IOException {
        File file = new File(fileName);
        try {
            FileUtils.writeStringToFile(file, toStringCatalogo(catalogo), Charset.defaultCharset());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String toStringCatalogo(List<ElementoCatalogo> catalogo) {
        return catalogo.stream()
                .map(elemento -> {
                    if (elemento instanceof Libro) {
                        Libro libro = (Libro) elemento;
                        return libro.getCodiceISBN() + "@" + libro.getTitolo() + "@" +
                                libro.getAnnoPubblicazione() + "@" + libro.getNumeroPagine() + "@" +
                                "Libro@" + libro.getAutore() + "@" + libro.getGenere();
                    } else if (elemento instanceof Rivista) {
                        Rivista rivista = (Rivista) elemento;
                        return rivista.getCodiceISBN() + "@" + rivista.getTitolo() + "@" +
                                rivista.getAnnoPubblicazione() + "@" + rivista.getNumeroPagine() + "@" +
                                "Rivista@" + rivista.getPeriodicita();
                    }
                    return "";
                })
                .collect(Collectors.joining("#"));
    }
    public static CatalogoBibliotecario caricaDaDisco(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        try {
            String str = FileUtils.readFileToString(file, Charset.defaultCharset());

            String[] catalogoStr =  str.split("#");
            for (int i = 0; i < catalogoStr.length; i++){
                String[] elementoStr = catalogoStr[i].split("@");
                Arrays.stream(elementoStr).forEach(s -> System.out.println(s));
            }

//            Arrays.stream(catalogoStr).forEach(s -> System.out.println(s));

        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }
    public void stampaElemento(ElementoCatalogo elemento) {
        System.out.println("Elemento trovato:");
        System.out.println(elemento.toString());
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CatalogoBibliotecario catalogo = new CatalogoBibliotecario();
        catalogo.aggiungiLibro(new Libro("1234567890", "Harry Potter e la pietra filosofale", 1998, 304, "J.K.Rowling", "Fantasy"));

        catalogo.aggiungiRivista(new Rivista("9876543210", "La storia di Harry Potter", 2020, 100, Periodicita.SEMESTRALE ));

        System.out.println("--------------------------------------------------------");
        Optional<ElementoCatalogo> e1 = catalogo.cercaPerISBN("1234567890");
        System.out.println(e1);
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------------------------------------------------");
        List<ElementoCatalogo> e2 =  catalogo.cercaPerAnnoPubblicazione(1998);
        System.out.println(e2);
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------------------------------------------------");
        List<ElementoCatalogo> e3 = catalogo.cercaPerAutore("J.K.Rowling");
        catalogo.cercaPerAutore("e3");
        System.out.println("--------------------------------------------------------");

        catalogo.salvaSuDisco("./../savedCatalog/catalogo.txt");
        caricaDaDisco("./../savedCatalog/catalogo.txt");
    }
}
