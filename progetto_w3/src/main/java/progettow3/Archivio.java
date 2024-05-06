package progettow3;


import progettow3.entities.*;
import progettow3.enums.Periodicita;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Archivio {
    private List<ElementoCatalogo> catalogo;
    private List<Prestito> prestiti;

    public Archivio() {
        this.catalogo = new ArrayList<>();
        this.prestiti = new ArrayList<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        catalogo.add(elemento);
    }

    public void rimuoviElemento(int codiceISBN) {
        catalogo.removeIf(elemento -> elemento.getCodiceISBN() == codiceISBN);
    }

    public ElementoCatalogo ricercaPerISBN(int codiceISBN) {
        for (ElementoCatalogo elemento : catalogo) {
            if (elemento.getCodiceISBN() == codiceISBN) {
                return elemento;
            }
        }
        return null;
    }

    public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int anno) {
        List<ElementoCatalogo> risultati = new ArrayList<>();
        for (ElementoCatalogo elemento : catalogo) {
            if (elemento.getDataPubblicazione().getYear() == anno) {
                risultati.add(elemento);
            }
        }
        return risultati;
    }

    public List<ElementoCatalogo> ricercaPerAutore(String autore) {
        List<ElementoCatalogo> risultati = new ArrayList<>();
        for (ElementoCatalogo elemento : catalogo) {
            if (elemento instanceof Libro) {
                Libro libro = (Libro) elemento;
                if (libro.getAutore().equals(autore)) {
                    risultati.add(elemento);
                }
            }
        }
        return risultati;
    }

    public List<ElementoCatalogo> ricercaPerTitolo(String titolo) {
        List<ElementoCatalogo> risultati = new ArrayList<>();
        for (ElementoCatalogo elemento : catalogo) {
            if (elemento.getTitolo().toLowerCase().contains(titolo.toLowerCase())) {
                risultati.add(elemento);
            }
        }
        return risultati;
    }

    public void effettuaPrestito(Utente utente, ElementoCatalogo elemento, LocalDate dataInizioPrestito) {
        Prestito prestito = new Prestito();
        prestito.setUtente(utente);
        prestito.setElementoPrestato(elemento);
        prestito.setDataInizioPrestito(dataInizioPrestito);

        prestito.setDataRestituzionePrevista(null);
        prestito.setDataRestituzioneEffettiva(null);

        utente.getPrestiti().add(prestito);
    }

    public List<Prestito> ricercaPrestitiUtente(int numeroTessera) {
        List<Prestito> risultati = new ArrayList<>();
        for (Prestito prestito : prestiti) {
            if (prestito.getUtente().getNumeroDiTessera() == numeroTessera) {
                risultati.add(prestito);
            }
        }
        return risultati;
    }

    public List<Prestito> ricercaPrestitiScaduti() {
        List<Prestito> risultati = new ArrayList<>();
        LocalDate dataAttuale = LocalDate.now();
        if (prestiti != null) {
            for (Prestito prestito : prestiti) {
                LocalDate dataRestituzionePrevista = prestito.getDataRestituzionePrevista();
                LocalDate dataRestituzioneEffettiva = prestito.getDataRestituzioneEffettiva();
                if (dataRestituzionePrevista != null && dataRestituzionePrevista.isBefore(dataAttuale) && dataRestituzioneEffettiva == null) {
                    risultati.add(prestito);
                }
            }
        }
        return risultati;
    }
    public static void main(String[] args) {
        Archivio archivio = new Archivio();

        Libro libro1 = new Libro(67890, "Il signore degli anelli", LocalDate.of(1954, 7, 29), 1170, "J.R.R. Tolkien", "Fantasy");
        Libro libro2 = new Libro(22097, "Harry Potter e la pietra filosofale", LocalDate.of(1997, 6, 26), 332, "J.K. Rowling", "Fantasy");
        Libro libro3 = new Libro(12865, "1984", LocalDate.of(1949, 6, 8), 328, "George Orwell", "Distopia");

        archivio.aggiungiElemento(libro1);
        archivio.aggiungiElemento(libro2);
        archivio.aggiungiElemento(libro3);

        Rivista rivista1 = new Rivista(45001, "National Geographic", LocalDate.of(1888, 10, 15), 200, Periodicita.MENSILE);
        archivio.aggiungiElemento(rivista1);

        Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 15));
        archivio.effettuaPrestito(utente1, libro1, LocalDate.now());

        List<Prestito> prestitiUtente = archivio.ricercaPrestitiUtente(utente1.getNumeroDiTessera());

        List<Prestito> prestitiScaduti = archivio.ricercaPrestitiScaduti();

        List<ElementoCatalogo> libriDiTolkien = archivio.ricercaPerAutore("J.R.R. Tolkien");

        List<ElementoCatalogo> libriConTitolo1984 = archivio.ricercaPerTitolo("1984");

        List<ElementoCatalogo> libriDel1949 = archivio.ricercaPerAnnoPubblicazione(1949);
    }
}
