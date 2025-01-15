package fr.eni.lodo.services;

import fr.eni.lodo.interfaces.ClientInterface;
import fr.eni.lodo.interfaces.ExemplaireInterface;
import fr.eni.lodo.models.Client;
import fr.eni.lodo.models.ExemplaireJeu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExemplaireService implements ExemplaireInterface {

    private List<ExemplaireJeu> exemplaireJeux;

    public ExemplaireService() {

    }

    @Override
    public void ajouterExemplaire(ExemplaireJeu exemplaireJeu) {
//        exemplaireJeu.clone();
//        this.clients.add(client);
    }

    @Override
    public void supprimerExemplaire(ExemplaireJeu exemplaireJeu) {
        this.exemplaireJeux.remove(exemplaireJeu);
    }
}
