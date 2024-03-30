package org.example.Hibernet;

import org.example.Hibernet.Entities.Client;
import org.example.Hibernet.Entities.Planet;
import org.example.Hibernet.Entities.Ticket;
import org.example.Hibernet.Services.ClientCrudService;
import org.example.Hibernet.Services.PlanetCrudService;
import org.example.Hibernet.Services.PropReader;
import org.example.Hibernet.Services.StartConfiguration;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {


       // StartConfiguration startConf = StartConfiguration.getInstance();
        //startConf.doFlywayMigrations(PropReader.urlForFlyway(),PropReader.userNameForFlyway(),PropReader.passwordForFlyway());

        PlanetCrudService pcs = new PlanetCrudService();
        Planet newPlanet = new Planet();

        // Create
        newPlanet.setId("NEP34");
        newPlanet.setName("Neptun");
        //System.out.println(pcs.createPlanet(newPlanet));

        //Update
//        newPlanet.setName("Neptun123");
//        System.out.println(pcs.updatePlanet(newPlanet));

        //ReadById
        //System.out.println(pcs.getPlanetById("NEP34"));

        //Read all objects
//        List<Planet> planets = pcs.getAllPlanets();
//        for (Planet planet:planets) {
//            System.out.println(planet);
//        }


        //Delete by id
       // System.out.println(pcs.deletePlanetById("NEP34"));

        //Delete object
        //pcs.deletePlanet(newPlanet);


        ClientCrudService ccs = new ClientCrudService();
        Client newClient = new Client();

        //Create
       newClient.setName("Zmiy Gorinich");

        long idNewClient = ccs.createClient(newClient);
         System.out.println(idNewClient);
        System.out.println(newClient);

        //Update
        newClient.setName("Meduse Gargona");
        System.out.println(ccs.updateClient(idNewClient, newClient));

        //Delete by id
         //System.out.println(ccs.deleteClientById(46L));

        //Read
        System.out.println(ccs.getClientById(47L));

        //ReadAll
        List<Client> clients = ccs.getAllClients();
        for (Client client :clients) {
            System.out.println(client);
        }

    }

}