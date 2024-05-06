package progettow3.entities;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "elementi_catalogo")
//crea tante tabelle quante sono le sottoclassi complete di tutti i dati anche della superclasse
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ElementoCatalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "elementi_catalogo_codiceISBN_table")
    @TableGenerator(name = "elementi_catalogo_codiceISBN_table",initialValue = 0, allocationSize = 1)
    private int codiceISBN;

    private String titolo;
    private LocalDate dataPubblicazione;
    @Column(name = "numero_pagine")
    private int numeroPagine;


    public ElementoCatalogo (){

    }
    public ElementoCatalogo(int codiceISBN, String titolo, LocalDate dataPubblicazione, int numeroPagine) {
        this.codiceISBN = codiceISBN;
        this.titolo = titolo;
        this.dataPubblicazione = dataPubblicazione;
        this.numeroPagine = numeroPagine;
    }
    public int getCodiceISBN() {

        return codiceISBN;
    }
    public void setCodiceISBN(int codiceISBN) {

        this.codiceISBN = codiceISBN;
    }
    public String getTitolo() {

        return titolo;
    }
    public void setTitolo(String titolo) {

        this.titolo = titolo;
    }
    public LocalDate getDataPubblicazione() {

        return dataPubblicazione;
    }
    public void setDataPubblicazione(LocalDate dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }
    public int getNumeroPagine() {
        return numeroPagine;
    }
    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "ElementoCatalogo{" +
                "codiceISBN=" + codiceISBN +
                ", titolo='" + titolo + '\'' +
                ", dataPubblicazione=" + dataPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
