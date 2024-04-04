package org.example.Hibernet.Services;

import org.example.Hibernet.DAO.PlanetDAO;
import org.example.Hibernet.Entities.Planet;
import org.example.Hibernet.Entities.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlanetCrudService implements PlanetDAO {
    @Override
    public boolean createPlanet(Planet planet ){
        boolean result = false;
        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                planet.setId(planet.getId());
                session.persist(planet );
                transaction.commit();
                result = true;
            } catch (Exception ex){
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return result;
    }
    @Override
    public boolean updatePlanet (Planet  planet ){
        boolean result = false;
        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                session.merge(planet);
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
    public Planet  getPlanetById(String  planetId){

        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            return session.get(Planet.class, planetId);
        }

    }
    @Override
    public List<Planet> getAllPlanets(){
        try(Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            return session.createQuery("from Planet",Planet.class).list();
        }
    }
    @Override
    public boolean deletePlanetById(String planetId){
        boolean result = false;
        try(Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try{
               Planet needPlanet = session.get(Planet.class, planetId);
               session.remove(needPlanet);
               transaction.commit();
               result=true;
           }catch (Exception ex){
               ex.printStackTrace();
               transaction.rollback();
           }
            return result;
        }

    }
    @Override
    public  void deletePlanet (Planet  planet ){
        try(Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        }
    }

    public List<Ticket> getTicketsFromPlanet(String planetId){
        String hql = "SELECT p.ticketsFromPlanet FROM Planet p WHERE p.id = :planetId";
        try(Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            return session.createQuery(hql,Ticket.class).setParameter("planetId", planetId).getResultList();
        }

    }
}
