package org.example.Hibernet.Entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Column()
    @Size(min = 3, max = 200)
    String name;

    @OneToMany (mappedBy = "clientId", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

}
