package it.epicode.gestionePrenotazioni.repository;

import it.epicode.gestionePrenotazioni.enums.TipoPostazione;
import it.epicode.gestionePrenotazioni.beans.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Integer> {
    List<Postazione> findByTipoPostazioneAndEdificio_Citta(TipoPostazione tipoPostazione, String citta);
}
