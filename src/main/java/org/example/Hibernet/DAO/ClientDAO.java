package org.example.Hibernet.DAO;

import org.example.Hibernet.Entities.Client;

import java.util.List;

public interface ClientDAO {
    long createClient(Client client );
    boolean updateClient (Long  clientId, Client client);
    Client  getClientById(Long  clientId);
    List<Client> getAllClients();
    boolean deleteClientById(Long clientId);

}
