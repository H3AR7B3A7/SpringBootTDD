package be.dog.d.steven.springboottdd1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Nested
    @DisplayName("Given a car")
    class CarTest {

        @Nested
        @DisplayName("When the car is saved")
        class ReturnCarTest {
            @Test
            @DisplayName("Then we can find it by name")
            void getCar_returnsCarDetails() {
                Car savedCar = entityManager.persistFlushFind(new Car("tesla", "electric"));
                Car car = carRepository.findByName("tesla");
                assertThat(car.getName()).isEqualTo(savedCar.getName());
                assertThat(car.getType()).isEqualTo(savedCar.getType());
            }
        }
    }
}
