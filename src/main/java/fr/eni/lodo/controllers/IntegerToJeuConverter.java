package fr.eni.lodo.controllers;

import fr.eni.lodo.models.Jeu;
import fr.eni.lodo.services.JeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IntegerToJeuConverter implements Converter<Integer, Jeu> {

    @Autowired
    private JeuService jeuService;

    @Override
    public Jeu convert(Integer source) {
        return jeuService.findOneById(source);
    }
}
