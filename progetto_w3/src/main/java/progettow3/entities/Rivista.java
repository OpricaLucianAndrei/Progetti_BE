package progettow3.entities;

import progettow3.enums.Periodicita;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Rivista extends ElementoCatalogo{
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;


    public Rivista(int codiceISBN, String titolo, LocalDate annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }
    public Rivista (){}
    public Periodicita getPeriodicita() {
        return periodicita;
    }
    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Periodicità: " + getPeriodicita();
    }

}



