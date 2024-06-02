package it.epicode.gestioneEventi.service;

import it.epicode.gestioneEventi.Dto.EventoDto;
import it.epicode.gestioneEventi.entity.Evento;
import it.epicode.gestioneEventi.entity.User;
import it.epicode.gestioneEventi.exceptions.EventoNonTrovatoException;
import it.epicode.gestioneEventi.exceptions.NumeroMassimoPartecipantiRaggiunto;
import it.epicode.gestioneEventi.repository.EventoRepository;
import it.epicode.gestioneEventi.repository.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(int id) {
        return eventoRepository.findById(id);
    }


    public String salvaEvento(EventoDto eventoDto) {
        Evento evento = new Evento();
        evento.setData(eventoDto.getData());
        evento.setTitolo(eventoDto.getTitolo());
        evento.setDescrizione(eventoDto.getDescrizione());
        evento.setMaxPosti(eventoDto.getMaxPosti());
        evento.setLuogo(eventoDto.getLuogo());

        List<User> partecipanti = userRepository.findByIdIn(eventoDto.getPartecipanti());

        if (partecipanti.size() > evento.getMaxPosti()) {
            throw new NumeroMassimoPartecipantiRaggiunto("Il numero massimo di partecipanti è già stato raggiunto per questo evento");
        }

        for (User partecipante : partecipanti) {
            partecipante.getEventi().add(evento);
        }

        evento.setPartecipanti(partecipanti);
        eventoRepository.save(evento);

        for (User partecipante : partecipanti) {
            userRepository.save(partecipante);
        }

        evento.setNumeroPostiDisponibili(evento.getMaxPosti() - evento.getPartecipanti().size());

        return "Evento con ID:" + evento.getId() + " è stato salvato correttamente";
    }

    public Evento aggiornaEvento(int id, EventoDto eventoDto) {
        Optional<Evento> eventoOptional = getEventoById(id);

        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            evento.setData(eventoDto.getData());
            evento.setLuogo(eventoDto.getLuogo());
            evento.setTitolo(eventoDto.getTitolo());
            evento.setDescrizione(eventoDto.getDescrizione());
            evento.setMaxPosti(eventoDto.getMaxPosti());
            evento.getPartecipanti().clear();

            List<User> partecipanti = userRepository.findByIdIn(eventoDto.getPartecipanti());

            Set<User> utenti = new HashSet<>(partecipanti);

            if (utenti.size() > evento.getMaxPosti()) {
                throw new NumeroMassimoPartecipantiRaggiunto("Il numero massimo di partecipanti è già stato raggiunto per questo evento");
            }


            for (User partecipante : utenti) {
                if (!evento.getPartecipanti().contains(partecipante)) {
                    partecipante.getEventi().add(evento);
                    evento.getPartecipanti().add(partecipante);
                }
            }

            evento.setNumeroPostiDisponibili(evento.getMaxPosti() - evento.getPartecipanti().size());
            eventoRepository.save(evento);

            return evento;
        } else {
            throw new EventoNonTrovatoException("Non è possibile aggiornare l'evento siccome non è stato trovato");
        }
    }


    public String eliminaEvento(int id) {
        Optional<Evento> eventoOptional = getEventoById(id);

        if (eventoOptional.isPresent()) {
            eventoRepository.deleteById(id);
            return "Evento con ID:" + id + " è stato eliminato";
        } else {
            throw new EventoNonTrovatoException("Evento con ID: " + id + " non è stato trovato");
        }
    }
}
