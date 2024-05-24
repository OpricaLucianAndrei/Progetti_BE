package it.epicode.gestioneDispositivi.repository;


import it.epicode.gestioneDispositivi.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
}
