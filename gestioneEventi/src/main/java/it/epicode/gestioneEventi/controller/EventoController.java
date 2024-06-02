package it.epicode.gestioneEventi.controller;
import it.epicode.gestioneEventi.Dto.EventoDto;
import it.epicode.gestioneEventi.entity.Evento;
import it.epicode.gestioneEventi.exceptions.BadRequestException;
import it.epicode.gestioneEventi.exceptions.UserNonTrovatoException;
import it.epicode.gestioneEventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/eventi")
    @PreAuthorize("hasAnyAuthority('UTENTE_NORMALE','ORGANIZZATORE_DI_EVENTI')")
    public List<Evento> getAllEvento() {
        return eventoService.getAllEventi();
    }

    @GetMapping("/eventi/{id}")
    @PreAuthorize("hasAnyAuthority('UTENTE_NORMALE','ORGANIZZATORE_DI_EVENTI')")
    public Evento getEventoById(@PathVariable int id) {
        Optional<Evento> eventoOptional = eventoService.getEventoById(id);
        if (eventoOptional.isPresent()) {
            return eventoOptional.get();
        } else {
            throw new UserNonTrovatoException("Evento con ID: " + id + " non è stato trovato");
        }
    }

    @PostMapping("/eventi")
    @PreAuthorize("hasAnyAuthority('UTENTE_NORMALE','ORGANIZZATORE_DI_EVENTI')")
    public String salvaEvento(@RequestBody EventoDto eventoDto) {
        return eventoService.salvaEvento(eventoDto);
    }

    @PutMapping("/eventi/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public Evento aggiornaEvento(@PathVariable int id, @RequestBody @Validated EventoDto eventoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s + s2));
        }
        return eventoService.aggiornaEvento(id, eventoDto);
    }

    @DeleteMapping("/eventi/{id}")
    @PreAuthorize("hasAnyAuthority('UTENTE_NORMALE','ORGANIZZATORE_DI_EVENTI')")
    public String eliminaEvento(@PathVariable int id) {
        return eventoService.eliminaEvento(id);
    }
}
