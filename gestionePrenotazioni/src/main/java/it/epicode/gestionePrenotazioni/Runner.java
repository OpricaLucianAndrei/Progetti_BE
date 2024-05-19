package it.epicode.gestionePrenotazioni;

import it.epicode.gestionePrenotazioni.beans.Edificio;
import it.epicode.gestionePrenotazioni.beans.Postazione;
import it.epicode.gestionePrenotazioni.beans.Utente;

import it.epicode.gestionePrenotazioni.enums.TipoPostazione;
import it.epicode.gestionePrenotazioni.service.EdificioService;
import it.epicode.gestionePrenotazioni.service.PostazioneService;
import it.epicode.gestionePrenotazioni.service.PrenotazioneService;
import it.epicode.gestionePrenotazioni.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@PropertySource("application.properties")
public class Runner implements CommandLineRunner {
    @Autowired
    private EdificioService edificioService;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private UtenteService utenteService;




    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx=new AnnotationConfigApplicationContext(GestionePrenotazioniApplication.class);

        Edificio edificio1 = ctx.getBean(Edificio.class);
        edificio1.setNome("Edificio Epicode");
        edificio1.setIndirizzo("Via dei Magazzini Generali 16");
        edificio1.setCitta("Roma");
        edificioService.inserisciEdificio(edificio1);


        Postazione postazione1 = ctx.getBean(Postazione.class);
        postazione1.setCodiceUnivoco(23);
        postazione1.setDescrizione("Postazione Privata");
        postazione1.setTipoPostazione(TipoPostazione.PRIVATO);
        postazione1.setNumeroMaxPartecipanti(1);
        postazione1.setEdificio(edificio1);
        postazioneService.inserisciPostazione(postazione1);


        Utente utente1 = ctx.getBean(Utente.class);
        utente1.setUsername("lucian2301");
        utente1.setNomeCompleto("Oprica Lucian Andrei");
        utente1.setEmail("opricalucian4@gmail.com");
        utenteService.inserisciUtente(utente1);


        Postazione postazione2 = ctx.getBean(Postazione.class);
        postazione2.setCodiceUnivoco(40);
        postazione2.setDescrizione("Postazione Open Space");
        postazione2.setTipoPostazione(TipoPostazione.OPENSPACE);
        postazione2.setNumeroMaxPartecipanti(40);
        postazione2.setEdificio(edificio1);
        postazioneService.inserisciPostazione(postazione2);


        try {
            prenotazioneService.prenotaPostazione(utente1.getUsername(), postazione1.getCodiceUnivoco(),LocalDate.now());
            System.out.println("Prenotazione effettuata con successo!");
        } catch (Exception e) {
            System.err.println("Errore durante la prenotazione: " + e.getMessage());
        }


        List<Postazione> postazioni = postazioneService.trovaPostazioniPerTipoEdificio(TipoPostazione.PRIVATO, "Roma");
        System.out.println("Postazioni trovate:");
        for (Postazione postazione : postazioni) {
            System.out.println(postazione);

        }

    }



}