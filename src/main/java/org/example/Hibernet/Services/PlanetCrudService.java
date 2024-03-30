package org.example.Hibernet.Services;

import org.example.Hibernet.DAO.PlanetDAO;
import org.example.Hibernet.Entities.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

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
        Planet planet;
        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            planet = session.get(Planet.class, planetId);
        }
        return planet;
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
}
