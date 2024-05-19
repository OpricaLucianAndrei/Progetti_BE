package it.epicode.gestionePrenotazioni.repository;

import it.epicode.gestionePrenotazioni.beans.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
}