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

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenre(int id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.orElse(null);
    }

    public Genre getGenreByLibelle(String libelle) {
        Optional<Genre> genre = genreRepository.findByLibelle(libelle);
        return genre.orElse(null);
    }
}
