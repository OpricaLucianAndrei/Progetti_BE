package it.epicode.gestioneEventi.service;

import it.epicode.gestioneEventi.Dto.UserDto;
import it.epicode.gestioneEventi.entity.User;
import it.epicode.gestioneEventi.enums.Role;
import it.epicode.gestioneEventi.exceptions.UserNonTrovatoException;
import it.epicode.gestioneEventi.repository.EventoRepository;
import it.epicode.gestioneEventi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> getAllUtenti() {
        return userRepository.findAll();
    }

    public Optional<User> getUtenteById(int id) {
        return userRepository.findById(id);
    }


    public User getUtenteByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNonTrovatoException("Utente con EMAIL: " + email + " non è stato trovato");
        }
    }


    public String salvaUtente(UserDto utenteDto) {
        User utente = new User();
        utente.setEmail(utenteDto.getEmail());
        utente.setNome(utenteDto.getNome());
        utente.setCognome(utenteDto.getCognome());

        utente.setRuolo(Role.UTENTE_NORMALE);

        utente.setPassword(passwordEncoder.encode(utenteDto.getPassword()));
        userRepository.save(utente);

        return "Utente con ID:" + utente.getId() + " è stato salvato correttamente";
    }

    public User aggiornaUtente(int id, UserDto userDto) {
        Optional<User> userOptional = getUtenteById(id);

        if (userOptional.isPresent()) {
            User utente = userOptional.get();
            utente.setEmail(userDto.getEmail());
            utente.setNome(userDto.getNome());
            utente.setCognome(userDto.getCognome());

            utente.setPassword(passwordEncoder.encode(userDto.getPassword()));

            return userRepository.save(utente);
        } else {
            throw new UserNonTrovatoException("Non è possibile aggiornare l'utente siccome non è stato trovato");
        }
    }

    public String eliminaUtente(int id) {
        Optional<User> userOptional = getUtenteById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return "Utente con ID: \" id \" è stato eliminato";
        } else {
            throw new UserNonTrovatoException("Utente con ID: " + id + " non è stato trovato");
        }
    }
}
