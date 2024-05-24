package it.epicode.gestioneDispositivi.service;


import it.epicode.gestioneDispositivi.Dto.DispositivoDto;
import it.epicode.gestioneDispositivi.enums.StatoDispositivo;
import it.epicode.gestioneDispositivi.exception.DipendenteNonTrovatoException;
import it.epicode.gestioneDispositivi.exception.DispositivoNonTrovatoException;
import it.epicode.gestioneDispositivi.model.Dipendente;
import it.epicode.gestioneDispositivi.model.Dispositivo;
import it.epicode.gestioneDispositivi.repository.DipendenteRepository;
import it.epicode.gestioneDispositivi.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public  String saveDispositivo(DispositivoDto dispositivoDto) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipo(dispositivoDto.getTipo());
        dispositivo.setStato(dispositivoDto.getStatoDispositivo());
        dispositivoRepository.save(dispositivo);
        return "Dispositivo salvato con successo con id " + dispositivo.getId();
    }

    public Page<Dispositivo> getBlogPostList(int page, int size, String sortBy ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dispositivoRepository.findAll(pageable);
    }

    public Optional<Dispositivo> getDispositivoById(int id) {
        return dispositivoRepository.findById(id);
    }

    public Dispositivo updateDispositivo(int id, DispositivoDto dispositivoDto) throws DispositivoNonTrovatoException {
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);

        if (dispositivoOptional.isPresent()) {
            Dispositivo dispositivo = dispositivoOptional.get();
            dispositivo.setTipo(dispositivoDto.getTipo());
            dispositivo.setStato(dispositivoDto.getStatoDispositivo());
            dispositivoRepository.save(dispositivo);
            return dispositivo;

        } else {
            throw new DispositivoNonTrovatoException("Dispositivo con id " + id + " non trovato");
        }
    }

    public String deleteDispositivo(int id) throws DispositivoNonTrovatoException {
        Optional<Dispositivo> dispositivoOptional = dispositivoRepository.findById(id);
        if (dispositivoOptional.isPresent()) {
            dispositivoRepository.delete(dispositivoOptional.get());
            return "Post con id "+ id +" cancellato con successo";
        } else {
            throw new DispositivoNonTrovatoException("Post con id "+ id +" non trovato");
        }
    }

    public String patchAggiuntaDipendenteAlDispositivo(int id, int dipendenteId) throws DispositivoNonTrovatoException, DipendenteNonTrovatoException {
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);
        if (dispositivoOptional.isPresent()) {
            Dispositivo dispositivo = dispositivoOptional.get();
            Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(dipendenteId);
            if (dipendenteOptional.isPresent()) {
                Dipendente dipendente = dipendenteOptional.get();
                dispositivo.setDipendente(dipendente);
                dispositivo.setStato(StatoDispositivo.ASSEGNATO);
                dispositivoRepository.save(dispositivo);
                return "Dispositivo con id " + id + " aggiornato con successo";
            } else {
                throw new DipendenteNonTrovatoException("Dipendente con id " + dipendenteId + " non trovato");
            }
        } else {
            throw new DispositivoNonTrovatoException("Dispositivo con id " + id + " non trovato");
        }
    }


}
