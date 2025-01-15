package fr.eni.lodo.services;

import fr.eni.lodo.interfaces.JeuInterface;
import fr.eni.lodo.models.Genre;
import fr.eni.lodo.models.Jeu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private List<Genre> genres;

    public GenreService() {
        this.genres = new ArrayList<>();

        Genre genre = new Genre();
        genre.setNo_genre(1);
        genre.setLibelle("Action");

        Genre genre2 = new Genre();
        genre2.setNo_genre(2);
        genre2.setLibelle("Carte");

        genres.add(genre);
        genres.add(genre2);

    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public Genre getGenre(int id) {
        for (Genre genre : genres) {
            if (genre.getNo_genre() == id) {
                return genre;
            }
        }
        return null;
    }
}
