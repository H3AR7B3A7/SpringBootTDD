package be.dog.d.steven.springboottdd1;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CachingTest {
    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Nested
    @DisplayName("Given a car")
    class CarTest {

        @Nested
        @DisplayName(("When the car is called twice"))
        class CallingTwiceTest {

            @Test
            @DisplayName("Then the car is only retrieved once")
            void caching() {
                given(carRepository.findByName(anyString())).willReturn(new Car("prius", "hybrid"));

                carService.getCarDetails("prius");
                carService.getCarDetails("prius");

                verify(carRepository, times(1)).findByName("prius");
            }
        }
    }
}
