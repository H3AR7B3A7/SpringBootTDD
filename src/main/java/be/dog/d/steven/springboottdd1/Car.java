package be.dog.d.steven.springboottdd1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;

    public Car(String name, String type) {
        this.name = name;
        this.type = type;
    }
}