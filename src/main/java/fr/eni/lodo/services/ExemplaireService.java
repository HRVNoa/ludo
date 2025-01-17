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
    public List<Exemplaire> findById(int id) {
        return exemplaireRepository.findById(id);
    }

    @Override
    public void save(Exemplaire exemplaire) {
        exemplaireRepository.save(exemplaire);
    }
}
