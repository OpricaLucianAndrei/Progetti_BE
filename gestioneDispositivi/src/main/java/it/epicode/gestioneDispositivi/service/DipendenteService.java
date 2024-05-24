package it.epicode.gestioneDispositivi.service;


import com.cloudinary.Cloudinary;
import it.epicode.gestioneDispositivi.Dto.DipendenteDto;
import it.epicode.gestioneDispositivi.exception.DipendenteNonTrovatoException;
import it.epicode.gestioneDispositivi.model.Dipendente;
import it.epicode.gestioneDispositivi.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    private String generateFotorUrl(String nome, String cognome) {
        return "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private JavaMailSenderImpl javaMailSender;


    public String saveDipendente(DipendenteDto dipendenteDto){
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dipendenteDto.getUsername());
        dipendente.setNome(dipendenteDto.getNome());
        dipendente.setCognome(dipendenteDto.getCognome());
        dipendente.setEmail(dipendenteDto.getEmail());
        dipendente.setFoto(generateFotorUrl(dipendente.getNome(), dipendente.getCognome()));
        dipendenteRepository.save(dipendente);
        sendMail(dipendente.getEmail(), dipendente.getId());
        return "Dipendente con Id: " + dipendente.getId() + " creato con successo";
    }

    public Page<Dipendente> getDipendenti(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }

    public Optional<Dipendente> getDipendenteById(int id){
        return dipendenteRepository.findById(id);
    }

    public Dipendente updateDipendente(int id, DipendenteDto dipendenteDto) throws DipendenteNonTrovatoException {
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);
        if(dipendenteOptional.isPresent()){
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setUsername(dipendenteDto.getUsername());
            dipendente.setNome(dipendenteDto.getNome());
            dipendente.setCognome(dipendenteDto.getCognome());
            dipendente.setEmail(dipendenteDto.getEmail());
            dipendente.setFoto(generateFotorUrl(dipendente.getNome(), dipendente.getCognome()));
            dipendenteRepository.save(dipendente);
            return dipendente;
        } else {
            throw new DipendenteNonTrovatoException("Dipendente con id " + id + " non trovato");
        }
    }

    public String deleteDipendente(int id) throws DipendenteNonTrovatoException {
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);
        if(dipendenteOptional.isPresent()){
            Dipendente dipendente = dipendenteOptional.get();
            dipendenteRepository.delete(dipendente);
            return "Dipendente con id " + id + " eliminato con successo";
        } else {
            throw new DipendenteNonTrovatoException("Dipendente con id " + id + " non trovato");
        }
    }

    public String patchFotoDipendente (int id, MultipartFile foto) throws DipendenteNonTrovatoException, IOException {
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);
        if(dipendenteOptional.isPresent()){
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setFoto(url);
            dipendenteRepository.save(dipendente);
            return "Foto dipendente con id " + id + " aggiornata con successo";
        } else {
            throw new DipendenteNonTrovatoException("Dipendente con id " + id + " non trovato");
        }
    }



    private void sendMail(String email, int id) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Nuovo Dipendente");
        message.setText("La sua registrazione alla nostra azienda è avvenuta con successo. Il suo id personale sarà: " + id);

        javaMailSender.send(message);
    }
}
