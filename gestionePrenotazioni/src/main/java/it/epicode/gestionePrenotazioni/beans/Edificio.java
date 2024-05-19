package it.epicode.gestionePrenotazioni.beans;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@Entity
@Component
public class Edificio {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String indirizzo;
    private String citta;


    @OneToMany(mappedBy = "edificio")
    private List<Postazione> postazioni ;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }


    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getCitta() {
        return citta;
    }
    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Override
    public String toString() {
        return "Edificio{" +
                "citta='" + citta + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }
}
