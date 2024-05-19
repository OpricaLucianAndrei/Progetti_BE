package it.epicode.gestionePrenotazioni.beans;

import lombok.Data;
import org.springframework.stereotype.Component;



import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity

@Component
public class Utente {

    @Id
    private String username;
    private String nomeCompleto ;
    private String email;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }
    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "email='" + email + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", Username='" + username + '\'' +
                '}';
    }
}
