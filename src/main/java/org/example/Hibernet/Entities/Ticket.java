package org.example.Hibernet.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;


//        created_at - TIMESTAMP в UTC, коли був створений цей квиток
//        client_id - ідентифікатор клієнта, якому належить цей квиток.
//        from_planet_id - ідентифікатор планети, звідки відправляється пасажир
//        to_planet_id - ідентифікатор планети, куди летить пасажир
@Entity
@Data
@Table(name="tickets")
public class Ticket {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column (name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeOfCreating;


    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client clientId;

    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanetID;

    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanetId;



}
