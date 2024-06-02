package it.epicode.gestioneEventi.repository;

import it.epicode.gestioneEventi.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
