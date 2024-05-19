package it.epicode.gestionePrenotazioni.repository;

import it.epicode.gestionePrenotazioni.beans.Postazione;
import it.epicode.gestionePrenotazioni.beans.Prenotazione;
import it.epicode.gestionePrenotazioni.beans.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
    List<Prenotazione> findByPostazioneAndData(Postazione postazione, LocalDate data);
    List<Prenotazione> findByUtenteAndData(Utente utente, LocalDate data);
}
