package it.epicode.gestioneDispositivi.controller;


import it.epicode.gestioneDispositivi.Dto.DispositivoDto;
import it.epicode.gestioneDispositivi.exception.BadRequestException;
import it.epicode.gestioneDispositivi.exception.DipendenteNonTrovatoException;
import it.epicode.gestioneDispositivi.exception.DispositivoNonTrovatoException;
import it.epicode.gestioneDispositivi.model.Dispositivo;
import it.epicode.gestioneDispositivi.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping("/api/dispositivi")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveDispositivo(@RequestBody @Validated DispositivoDto dispositivoDto, BindingResult bindingResult) throws DipendenteNonTrovatoException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return dispositivoService.saveDispositivo(dispositivoDto);
    }

    @GetMapping("/api/dispositivi")
    public Page<Dispositivo> getAllBlogPost(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "15") int size,
                                            @RequestParam(defaultValue = "id") String sortBy){

        return dispositivoService.getBlogPostList(page, size, sortBy);
    }

    @GetMapping("/api/dispositivi/{id}")
    public Dispositivo getDispositivoById(@PathVariable int id) throws DispositivoNonTrovatoException {
        Optional<Dispositivo> dispositivoOptional = dispositivoService.getDispositivoById(id);

        if( dispositivoOptional.isPresent()){
            return dispositivoOptional.get();
        } else {
            throw new DispositivoNonTrovatoException("Dispositivo con id " + id + " non trovato");
        }
    }

    @PutMapping("/api/dispositivi/{id}")
    public Dispositivo updateDispositivo(@PathVariable int id, @RequestBody @Validated DispositivoDto dispositivoDto, BindingResult bindingResult) throws DispositivoNonTrovatoException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return dispositivoService.updateDispositivo(id, dispositivoDto);
    }

    @DeleteMapping("/api/dispositivi/{id}")
    public String deleteDispositivo(@PathVariable int id) throws DispositivoNonTrovatoException {
        return dispositivoService.deleteDispositivo(id);
    }

    @PatchMapping("/api/dispositivi/{id}/dipendente/{dipendenteId}")
    public String patchAggiuntaDipendenteAlDispositivo(@PathVariable int id, @PathVariable int dipendenteId) throws DispositivoNonTrovatoException, DipendenteNonTrovatoException {
        return dispositivoService.patchAggiuntaDipendenteAlDispositivo(id, dipendenteId);
    }

}

