package progettow3.entities;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class Libro extends ElementoCatalogo{
    private String autore;
    private String genere;
    public Libro(int codiceISBN, String titolo, LocalDate dataPubblicazione, int numeroPagine, String autore, String genere) {
        super(codiceISBN, titolo, dataPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }
    public Libro(){}
    public String getAutore() {
        return autore;
    }
    public void setAutore(String autore) {
        this.autore = autore;
    }
    public String getGenere() {
        return genere;
    }
    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Autore: " + getAutore() + "\n" +
                "Genere: " + getGenere();
    }
}
