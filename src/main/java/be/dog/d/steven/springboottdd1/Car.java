package be.dog.d.steven.springboottdd1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Type is mandatory")
    private String type;

    public Car(String name, String type) {
        this.name = name;
        this.type = type;
    }
}