package fr.eni.lodo.interfaces;

import fr.eni.lodo.models.Exemplaire;

import java.util.List;

public interface ExemplaireInterface {

    Exemplaire findById(int id);

    void save(Exemplaire exemplaire);

    Exemplaire findAllById(int id);
}
