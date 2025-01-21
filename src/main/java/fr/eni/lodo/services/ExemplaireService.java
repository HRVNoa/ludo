package fr.eni.lodo.services;

import fr.eni.lodo.interfaces.ExemplaireInterface;
import fr.eni.lodo.models.Exemplaire;
import fr.eni.lodo.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemplaireService implements ExemplaireInterface {

    private List<Exemplaire> exemplaireJeux;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Override
    public Exemplaire findById(int id) {
        return exemplaireRepository.findById(id);
    }

    @Override
    public List<Exemplaire> findByIdJeu(int id) {
        return exemplaireRepository.findByIdJeu(id);
    }

    @Override
    public void save(Exemplaire exemplaire) {
        exemplaireRepository.save(exemplaire);
    }

    @Override
    public void supprimer(int id) {
        exemplaireRepository.supprimer(id);
    }

    @Override
    public boolean codebarreExiste(int no_exemplaire, String codebarre) {
        return exemplaireRepository.codebarreExiste(no_exemplaire, codebarre);
    }

    @Override
    public boolean codebarreExiste(String codebarre) {
        return exemplaireRepository.codebarreExiste(codebarre);
    }
}
