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
    public Genre convert(String libelle) {
        System.out.println("!!CONVERT!!");
        try {
            int id = Integer.parseInt(libelle);
            System.out.println(id);
            return genreService.getGenre(id);
        }catch (NumberFormatException numberFormatException ){
            System.out.println(String.valueOf(libelle.charAt(0)).toUpperCase()+libelle.substring(1));
            return genreService.getGenreByLibelle(String.valueOf(libelle.charAt(0)).toUpperCase()+libelle.substring(1));
        }
    }
}
