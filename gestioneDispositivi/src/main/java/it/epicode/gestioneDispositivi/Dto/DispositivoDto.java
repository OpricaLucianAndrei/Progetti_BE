package it.epicode.gestioneDispositivi.Dto;


import it.epicode.gestioneDispositivi.enums.StatoDispositivo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DispositivoDto {

    @NotNull(message = "Il campo tipo non può essere null")
    @Size(min = 2, max = 30)
    private String tipo;

    @NotNull(message = "Il campo statoDispositivo non può essere null")
    private StatoDispositivo statoDispositivo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public StatoDispositivo getStatoDispositivo() {
        return statoDispositivo;
    }

    public void setStatoDispositivo(StatoDispositivo statoDispositivo) {
        this.statoDispositivo = statoDispositivo;
    }

}
