package fr.eni.lodo.models;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

    private Integer no_jeu;
    @NotBlank
    private String titre;
    private String reference;
    private String description;
    private int tarif_journee;
    private int age_min;
    private int duree;
    private List<Genre> genres = new ArrayList<>();

    public Integer getNo_jeu() {
        return no_jeu;
    }

    public void setNo_jeu(Integer no_jeu) {
        this.no_jeu = no_jeu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTarif_journee() {
        return tarif_journee;
    }

    public void setTarif_journee(int tarif_journee) {
        this.tarif_journee = tarif_journee;
    }

    public int getAge_min() {
        return age_min;
    }

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenres(Genre genre) {
        this.genres.add(genre);
    }
}
