package progettow3.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "persone")
@NamedQuery(name = "getUtenteByName", query = "select p from Utente p where p.nome= :nome")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroDiTessera;

    private String nome;

    private String cognome;

    @Column(name = "data_di_nascita")
    private LocalDate dataDiNascita;



    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti = new ArrayList<>();


    public Utente(int numeroDiTessera, String nome, String cognome, LocalDate dataDiNascita) {
        this.numeroDiTessera = numeroDiTessera;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    public Utente(String nome, String cognome, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }


    public Utente() {
    }


    public int getNumeroDiTessera() {
        return numeroDiTessera;
    }

    public void setNumeroDiTessera(int numeroDiTessera) {
        this.numeroDiTessera = numeroDiTessera;
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

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "numeroDiTessera=" + numeroDiTessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", prestiti=" + prestiti +
                '}';
    }


}

