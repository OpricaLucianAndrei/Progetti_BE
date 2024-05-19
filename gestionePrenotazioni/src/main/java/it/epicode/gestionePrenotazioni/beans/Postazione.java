package it.epicode.gestionePrenotazioni.beans;

import jakarta.persistence.*;
import it.epicode.gestionePrenotazioni.enums.TipoPostazione;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity

@Component
public class Postazione {
    @Id
    private Integer codiceUnivoco;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoPostazione;
    private Integer numeroMaxPartecipanti;
    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    public Integer getCodiceUnivoco() {
        return codiceUnivoco;
    }


    public void setCodiceUnivoco(Integer codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoPostazione getTipoPostazione() {
        return tipoPostazione;
    }

    public void setTipoPostazione(TipoPostazione tipoPostazione) {
        this.tipoPostazione = tipoPostazione;
    }

    public Integer getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }

    public void setNumeroMaxPartecipanti(Integer numeroMaxPartecipanti) {
        this.numeroMaxPartecipanti = numeroMaxPartecipanti;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }



}
