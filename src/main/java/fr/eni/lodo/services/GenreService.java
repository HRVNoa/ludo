package fr.eni.lodo.services;

import fr.eni.lodo.interfaces.JeuInterface;
import fr.eni.lodo.models.Client;
import fr.eni.lodo.models.Genre;
import fr.eni.lodo.models.Jeu;
import fr.eni.lodo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private List<Genre> genres;

    @Autowired
    private GenreRepository genreRepository;

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
//        for (Genre genre : genres) {
//            if (genre.getNo_genre() == id) {
//                return genre;
//            }
//        }

        Optional<Genre> genre = genreRepository.findById(id);
        return genre.orElse(null);
    }

    public Genre getGenreByLibelle(String libelle) {
        Optional<Genre> genre = genreRepository.findByLibelle(libelle);
        return genre.orElse(null);
    }
}
