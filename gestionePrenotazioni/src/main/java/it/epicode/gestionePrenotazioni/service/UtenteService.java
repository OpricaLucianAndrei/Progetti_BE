package it.epicode.gestionePrenotazioni.service;


import it.epicode.gestionePrenotazioni.beans.Utente;
import it.epicode.gestionePrenotazioni.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;



    public void inserisciUtente(Utente utente) {
        utenteRepository.save(utente);
    }

    public Utente getUtente(String username) {
        return utenteRepository.findById(username).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
    }

    public List<Utente> getUtente(){
        return utenteRepository.findAll();
    }


    public void cancellaUtente(String userName){
        utenteRepository.deleteById(userName);

    }


}
