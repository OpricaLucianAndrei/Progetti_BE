package it.epicode.gestionePrenotazioni.beans;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Entity
@Data

@Component
public class Prenotazione {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "postazione_codice_univoco")
    private Postazione postazione;

    @ManyToOne
    @JoinColumn(name = "utente_username")
    private Utente utente;

    private LocalDate data;

    public Postazione getPostazione() {
        return postazione;
    }

    public void setPostazione(Postazione postazione) {
        this.postazione = postazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
