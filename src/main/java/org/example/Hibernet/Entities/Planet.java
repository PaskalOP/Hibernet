package org.example.Hibernet.Entities;

import jakarta.persistence.*;
import lombok.Data;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "planets")
public class Planet {

    @Id
    @Pattern(regexp = "[A-Z0-9]")
    String id;

    @Column()
    @Size(min=1,max = 500)
    String name;

}
