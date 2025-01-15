package fr.eni.lodo.services;

import fr.eni.lodo.interfaces.ClientInterface;
import fr.eni.lodo.models.Client;
import fr.eni.lodo.models.Jeu;
import fr.eni.lodo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientInterface {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findOneById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    @Override
    public void supprimerClient(Client client) {
        clientRepository.supprimer(client);
    }
}
