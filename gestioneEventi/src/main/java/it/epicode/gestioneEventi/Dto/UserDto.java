package it.epicode.gestioneEventi.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "il campo password non può essere vuoto/mancante/con soli spazi")
    private String nome;

    @NotBlank(message = "il campo password non può essere vuoto/mancante/con soli spazi")
    private String cognome;

    @Email
    @NotBlank(message = "il campo email non può essere vuoto/mancante/con soli spazi")
    private String email;

    @NotBlank(message = "il campo password non può essere vuoto/mancante/con soli spazi")
    private String password;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
