package it.epicode.gestioneEventi.controller;

import it.epicode.gestioneEventi.Dto.UserDto;
import it.epicode.gestioneEventi.entity.User;
import it.epicode.gestioneEventi.exceptions.BadRequestException;
import it.epicode.gestioneEventi.exceptions.UserNonTrovatoException;
import it.epicode.gestioneEventi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/utenti")
    @PreAuthorize("hasAnyAuthority('UTENTE_NORMALE','ORGANIZZATORE_DI_EVENTI')")
    public List<User> getAllUtenti() {
        return userService.getAllUtenti();
    }

    @GetMapping("/utenti/{id}")
    @PreAuthorize("hasAnyAuthority('UTENTE_NORMALE','ORGANIZZATORE_DI_EVENTI')")
    public User getUtenteById(@PathVariable int id) {
        Optional<User> userOptional = userService.getUtenteById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNonTrovatoException("Utente con ID: " + id + " non è ");
        }
    }

    @PutMapping("/utenti/{id}")
    @PreAuthorize("hasAnyAuthority('UTENTE_NORMALE','ORGANIZZATORE_DI_EVENTI')")
    public User aggiornaUtente(@PathVariable int id, @RequestBody @Validated UserDto utenteDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s + s2));
        }
        return userService.aggiornaUtente(id, utenteDto);
    }

    @DeleteMapping("/utenti/{id}")
    @PreAuthorize("hasAnyAuthority('UTENTE_NORMALE','ORGANIZZATORE_DI_EVENTI')")
    public String eliminaUtente(@PathVariable int id) {
        return userService.eliminaUtente(id);
    }
}