package it.epicode.gestioneEventi.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventoDto {

    @NotBlank(message = "il campo titolo non può essere vuoto/mancante/con soli spazi")
    private String titolo;

    private String descrizione;

    @NotNull(message = "il campo data non può essere null")
    private LocalDate data;

    @NotBlank(message = "il campo luogo non può essere vuoto/mancante/con soli spazi")
    private String luogo;

    @NotNull(message = "i posti massimi non possono essere nulli")
    private Integer maxPosti;

    @Min(value = 0, message = "Il numero di posti disponibili deve essere maggiore o uguale a zero (se i posti sono finiti)")
    private Integer numeroPostiDisponibili;

    private List<Integer> partecipanti;


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Integer getMaxPosti() {
        return maxPosti;
    }

    public void setMaxPosti(Integer maxPosti) {
        this.maxPosti = maxPosti;
    }

    public Integer getNumeroPostiDisponibili() {
        return numeroPostiDisponibili;
    }

    public void setNumeroPostiDisponibili(Integer numeroPostiDisponibili) {
        this.numeroPostiDisponibili = numeroPostiDisponibili;
    }

    public List<Integer> getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(List<Integer> partecipanti) {
        this.partecipanti = partecipanti;
    }
}
