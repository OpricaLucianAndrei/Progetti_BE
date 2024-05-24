package it.epicode.gestioneDispositivi.controller;


import it.epicode.gestioneDispositivi.Dto.DipendenteDto;
import it.epicode.gestioneDispositivi.exception.BadRequestException;
import it.epicode.gestioneDispositivi.exception.DipendenteNonTrovatoException;
import it.epicode.gestioneDispositivi.model.Dipendente;
import it.epicode.gestioneDispositivi.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/api/dipendenti")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveDipendente(@RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return dipendenteService.saveDipendente(dipendenteDto);
    }

    @GetMapping("/api/dipendenti")
    public Page<Dipendente> getTuttiDipendenti(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "15") int size,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        return dipendenteService.getDipendenti(page, size, sortBy);
    }

    @GetMapping("/api/dipendenti/{id}")
    public Dipendente getDipendenteById(@PathVariable int id) throws DipendenteNonTrovatoException {
        Optional<Dipendente> dipendenteOptional = dipendenteService.getDipendenteById(id);
        if (dipendenteOptional.isPresent()) {
            return dipendenteOptional.get();
        } else {
            throw new DipendenteNonTrovatoException("Dipendente con id "+ id + " non trovato");
        }
    }

    @PutMapping("/api/dipendenti/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteDto dipendenteDtoUpd, BindingResult bindingResult) throws DipendenteNonTrovatoException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return dipendenteService.updateDipendente(id, dipendenteDtoUpd);
    }

    @DeleteMapping("/api/dipendente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteDipendente(@PathVariable int id) throws DipendenteNonTrovatoException {
        return dipendenteService.deleteDipendente(id);
    }

    @PatchMapping("/api/dipendente/{id}")
    public String patchFoto(@RequestBody MultipartFile foto, @PathVariable int id) throws DipendenteNonTrovatoException, IOException {
        return dipendenteService.patchFotoDipendente(id, foto);

    }




}
