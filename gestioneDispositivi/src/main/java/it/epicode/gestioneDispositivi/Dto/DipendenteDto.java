package it.epicode.gestioneDispositivi.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DipendenteDto {

    @NotNull(message = "Il campo username non può essere null")
    @Size(min = 2, max = 30)
    private String username;

    @NotNull(message = "Il campo nome non può essere null")
    @Size(min = 2, max = 30)
    private String nome;

    @NotNull(message = "Il campo cognome non può essere null")
    @Size(min = 2, max = 30)
    private String cognome;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotNull(message = "Il campo email non può essere null")
    @Size(min = 8, max = 30)
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
}
