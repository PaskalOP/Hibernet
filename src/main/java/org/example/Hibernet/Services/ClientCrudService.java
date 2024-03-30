package org.example.Hibernet.Services;

import org.example.Hibernet.DAO.ClientDAO;
import org.example.Hibernet.Entities.Client;
import org.example.Hibernet.Entities.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ClientCrudService implements ClientDAO {


    @Override
    public long createClient(Client client) {
        Long id =-1L;
        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                client.setId(null);
                session.persist(client);
                transaction.commit();
                id = (Long)session.getIdentifier(client);

            } catch (Exception ex){
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return id;
    }

    @Override
    public boolean updateClient(Long clientId, Client client) {
        boolean result = false;
        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                client.setId(clientId);
                session.merge(client);
                transaction.commit();
                result =true;

            }catch (Exception ex){
                ex.printStackTrace();
                transaction.rollback();
            }
        }

        return result;
    }

    @Override
    public Client getClientById(Long clientId) {
        Client client;
        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            client = session.get(Client.class, clientId);
        }
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        try(Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            return session.createQuery("from Client",Client.class).list();
        }
    }

    @Override
    public boolean deleteClientById(Long clientId) {
        boolean result = false;
        try(Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                Client needClient= session.get(Client.class, clientId);
                session.remove(needClient);
                transaction.commit();
                result=true;
            }catch (Exception ex){
                ex.printStackTrace();
                transaction.rollback();
            }
            return result;
        }

    }

}
