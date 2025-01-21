package fr.eni.lodo.interfaces;

import fr.eni.lodo.models.Exemplaire;

import java.util.List;

public interface ExemplaireInterface {

    Exemplaire findById(int id);

    List<Exemplaire> findByIdJeu(int id);

    void save(Exemplaire exemplaire);

    void supprimer(int id);

    boolean codebarreExiste(int no_exemplaire, String codebarre);
}
