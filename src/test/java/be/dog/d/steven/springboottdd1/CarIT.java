package be.dog.d.steven.springboottdd1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:integrationTestData.sql")
class CarIT {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Nested
    @DisplayName("Given a car")
    class CarTest {
        
        @Nested
        @DisplayName("When the car is present")
        class CarExistsTest {
            @Test
            @DisplayName("Then it is returned with a 200 status")
            void getCar_returnsCarDetails() {
                // arrange

                // act
                ResponseEntity<Car> response = restTemplate.getForEntity("/cars/prius", Car.class);

                // assert
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                assertThat(response.getBody().getName()).isEqualTo("prius");
                assertThat(response.getBody().getType()).isEqualTo("hybrid");
            }
        }
    }
}