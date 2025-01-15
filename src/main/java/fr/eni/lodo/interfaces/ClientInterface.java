package fr.eni.lodo.interfaces;

import fr.eni.lodo.models.Client;

import java.util.List;

public interface ClientInterface {

    void save(Client client);

    List<Client> findAll();

    Client findOneById(int id);

    void supprimerClient(Client client);
}
