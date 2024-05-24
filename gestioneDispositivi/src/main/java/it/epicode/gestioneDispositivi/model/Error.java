package it.epicode.gestioneDispositivi.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class Error {
    private String message;
    private LocalDateTime dataErrore;
    private HttpStatus statoErrore;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDataErrore() {
        return dataErrore;
    }

    public void setDataErrore(LocalDateTime dataErrore) {
        this.dataErrore = dataErrore;
    }

    public HttpStatus getStatoErrore() {
        return statoErrore;
    }

    public void setStatoErrore(HttpStatus statoErrore) {
        this.statoErrore = statoErrore;
    }
}
