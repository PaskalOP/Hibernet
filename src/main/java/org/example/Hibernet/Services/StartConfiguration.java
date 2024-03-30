package org.example.Hibernet.Services;

import org.example.Hibernet.Entities.Client;
import org.example.Hibernet.Entities.Planet;
import org.example.Hibernet.Entities.Ticket;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StartConfiguration {
    private static final StartConfiguration INSTANCE=new StartConfiguration();
    private SessionFactory session;

    private StartConfiguration(){
        this.session = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

   public  static StartConfiguration getInstance(){
        return INSTANCE;
   }
    public SessionFactory getSessionFactory() {
        return this.session;
    }

    public void closeSessionFactory() {
        this.session.close();
    }

    public void doFlywayMigrations(String connectUrl, String userName, String password){
        Flyway flyway =  Flyway.configure().dataSource(connectUrl,userName,password).load();
        flyway.migrate();
    }


}
