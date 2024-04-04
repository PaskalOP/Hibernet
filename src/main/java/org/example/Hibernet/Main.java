package org.example.Hibernet;

import org.example.Hibernet.Entities.Client;
import org.example.Hibernet.Entities.Planet;
import org.example.Hibernet.Entities.Ticket;
import org.example.Hibernet.Services.*;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
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

//        List<Ticket> tickets = pcs.getTicketsFromPlanet("ERTH1");
//        for (Ticket ticket :tickets) {
//            System.out.println(ticket);
//        }
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
        //Client newClient = new Client();

        //Create
       //newClient.setName("Zmiy Gorinich");

//        long idNewClient = ccs.createClient(newClient);
//         System.out.println(idNewClient);
//        System.out.println(newClient);

        //Update
//        newClient.setName("Meduse Gargona");
//        System.out.println(ccs.updateClient(idNewClient, newClient));

        //Delete by id
         //System.out.println(ccs.deleteClientById(46L));

        //Read
        //System.out.println(ccs.getClientById(47L));

        //ReadAll
//        List<Client> clients = ccs.getAllClients();
//        for (Client client :clients) {
//            System.out.println(client);
//        }
        TicketCrudService tcs = new TicketCrudService();
        Ticket ticket = new Ticket();
        ticket.setClientId(ccs.getClientById(33L));
        ticket.setTimeOfCreating(LocalDateTime.of(2024,03,16,16,45));
        ticket.setFromPlanetID(pcs.getPlanetById("ERTH1"));
        ticket.setToPlanetId(pcs.getPlanetById("VENR23"));

        //createTicket
        //long idTisket = tcs.createTicket(ticket);

        //read ticket/tickets
        System.out.println(tcs.getTicketById(30L));
//        List<Ticket> tickets = tcs.getAllTickets();
//        for (Ticket ticket1:tickets) {
//            System.out.println(ticket1);
//        }

        //updateTicket
        //System.out.println(tcs.updateTicket(22L,ticket));

        //deleteTicket
        //System.out.println(tcs.deleteTicketById(32L));



    }

}