package org.example.Hibernet.DAO;

import org.example.Hibernet.Entities.Planet;
import org.example.Hibernet.Services.StartConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface PlanetDAO {
    boolean createPlanet(Planet planet );
    boolean updatePlanet (Planet  planet );
    Planet  getPlanetById(String  planetId);
    List<Planet> getAllPlanets();
    boolean deletePlanetById(String planetId);
    void deletePlanet (Planet  planet );

}
