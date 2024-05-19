package it.epicode.gestionePrenotazioni.repository;

import it.epicode.gestionePrenotazioni.beans.Utente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, String> {
}
