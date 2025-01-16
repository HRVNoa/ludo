package fr.eni.lodo.models;

import java.util.ArrayList;
import java.util.List;

public class Genre {

    private Integer no_genre;
    private String libelle;

    public Integer getNo_genre() {
        return no_genre;
    }

    public void setNo_genre(Integer no_genre) {
        this.no_genre = no_genre;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Genre : " + libelle;
    }
}
