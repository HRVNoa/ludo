package fr.eni.lodo.controllers;

import fr.eni.lodo.models.Genre;
import fr.eni.lodo.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToGenreConverter implements Converter<String, Genre> {

    @Autowired
    private GenreService genreService;

    @Override
    public Genre convert(String id) {
        return genreService.getGenre(Integer.parseInt(id));
    }
}
