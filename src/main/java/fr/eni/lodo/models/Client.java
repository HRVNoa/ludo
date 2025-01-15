package fr.eni.lodo.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Client {

    private Integer no_client;

    @NotBlank(message = "Veuillez saisir un nom.")
    private String nom;

    @NotBlank(message = "Veuillez saisir un prénom.")
    private String prenom;

    @NotBlank(message = "Veuillez saisir une adresse mail.")
    @Email(message = "Veuillez saisir une adresse valide.")
    @Size(max = 100, message = "L'adresse mail ne doit pas dépasser 100 caractères.")
    private String email;

    @Size(max = 10, message = "Le numéro de téléphone dois comporter exactement 10 chiffres.")
    private String no_tel;

    @NotBlank(message = "Veuillez saisir une rue.")
    private String rue;

    @NotBlank(message = "Veuillez saisir un code postal.")
    @Size(min = 5, max = 5, message = "Le code postal dois comporter exactement 5 chiffres.")
    private String code_postal;

    @NotBlank(message = "Veuillez saisir une ville.")
    private String ville;

    public Integer getNo_client() {
        return no_client;
    }

    public void setNo_client(Integer no_client) {
        this.no_client = no_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_tel() {
        return no_tel;
    }

    public void setNo_tel(String no_tel) {
        this.no_tel = no_tel;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Client{" +
                "no_client=" + no_client +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", no_tel='" + no_tel + '\'' +
                ", rue='" + rue + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
