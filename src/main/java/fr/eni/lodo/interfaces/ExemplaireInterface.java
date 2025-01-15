package fr.eni.lodo.interfaces;

import fr.eni.lodo.models.ExemplaireJeu;

import java.util.List;

public interface ExemplaireInterface {

    void ajouterExemplaire(ExemplaireJeu client);

    void supprimerExemplaire(ExemplaireJeu client);
}
