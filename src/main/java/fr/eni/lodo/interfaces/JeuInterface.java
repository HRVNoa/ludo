package fr.eni.lodo.interfaces;

import fr.eni.lodo.models.Client;
import fr.eni.lodo.models.Jeu;

import java.util.List;

public interface JeuInterface {

    void save(Jeu jeu);

    List<Jeu> findAll();

    Jeu findOneById(int id);

    void supprimerJeu(Jeu jeu);

}
