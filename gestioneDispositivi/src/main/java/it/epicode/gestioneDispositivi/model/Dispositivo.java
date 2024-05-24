package it.epicode.gestioneDispositivi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.gestioneDispositivi.enums.StatoDispositivo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Dispositivo {

    @Id
    @GeneratedValue
    private int id;

    private String tipo;

    @Enumerated(EnumType.STRING)
    private StatoDispositivo stato;


    @ManyToOne
    @JsonIgnore
    private Dipendente dipendente;

    public Dispositivo(String tipo, StatoDispositivo stato) {
        this.tipo = tipo;
        this.stato = stato;
    }

    public Dispositivo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public StatoDispositivo getStato() {
        return stato;
    }

    public void setStato(StatoDispositivo stato) {
        this.stato = stato;
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }
}
