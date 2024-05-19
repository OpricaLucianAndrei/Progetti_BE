package it.epicode.gestionePrenotazioni.service;

import it.epicode.gestionePrenotazioni.enums.TipoPostazione;
import it.epicode.gestionePrenotazioni.beans.Postazione;
import it.epicode.gestionePrenotazioni.repository.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;

    public void inserisciPostazione(Postazione postazione) {
        postazioneRepository.save(postazione);
    }

    public Postazione getPostazione(int id){
        return postazioneRepository.findById(id).get();
    }

    public List<Postazione> getPostazione(){
        return postazioneRepository.findAll();
    }


    public void cancellaPostazione(int id){
        postazioneRepository.deleteById(id);

    }
    public List<Postazione> trovaPostazioniPerTipoEdificio(TipoPostazione tipoPostazione, String citta) {
        return postazioneRepository.findByTipoPostazioneAndEdificio_Citta(tipoPostazione, citta);
    }
}
