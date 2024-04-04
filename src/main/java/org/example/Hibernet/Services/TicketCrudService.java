package org.example.Hibernet.Services;

import org.example.Hibernet.Entities.Client;
import org.example.Hibernet.Entities.Ticket;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TicketCrudService {
   public long createTicket(Ticket ticket ){
        Long id =-1L;
        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                ticket.setId(null);
                session.persist(ticket);
                transaction.commit();
                id = (Long)session.getIdentifier(ticket);

            } catch (Exception ex){
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return id;
    }
    public boolean updateTicket (Long  ticketId, Ticket ticket){
        boolean result = false;
        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                ticket.setId(ticketId);
                session.merge(ticket);
                transaction.commit();
                result =true;

            }catch (Exception ex){
                ex.printStackTrace();
                transaction.rollback();
            }
        }

        return result;
    }
    public Ticket  getTicketById(Long  ticketId){

        try (Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
           Ticket ticket = session.get(Ticket.class, ticketId);
           Hibernate.initialize(ticket.getClientId().getTickets());
            return ticket;

        }

    }
    public List<Ticket> getAllTickets(){
        try(Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            List<Ticket> tickets = session.createQuery("from Ticket",Ticket.class).list();

            for (Ticket ticket : tickets) {
                Hibernate.initialize(ticket.getFromPlanetID());
                Hibernate.initialize(ticket.getToPlanetId());
                Hibernate.initialize((ticket.getClientId().getTickets()));
            }
            return tickets;
        }
    }
    public boolean deleteTicketById(Long ticketId){
        boolean result = false;
        try(Session session = StartConfiguration.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                Ticket needTicket = session.get(Ticket.class, ticketId);
                session.remove(needTicket);
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
