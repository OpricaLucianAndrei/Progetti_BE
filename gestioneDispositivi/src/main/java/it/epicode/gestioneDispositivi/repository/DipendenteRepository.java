package it.epicode.gestioneDispositivi.repository;

import it.epicode.gestioneDispositivi.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {
}
