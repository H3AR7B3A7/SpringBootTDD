package be.dog.d.steven.springboottdd1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    
    @Mock
    private CarRepository carRepository;
    
    private CarService carService;
    
    
    @Nested
    @DisplayName("Given a car")
    class CarTest {
        @BeforeEach
        void setUp() {
            carService = new CarService(carRepository);
        }
        
        @Nested
        @DisplayName("When the car is present")
        class ExistingCarTest {
            @Test
            @DisplayName("Then we can get the details")
            void getCarDetails_shouldReturnCar() {
                given(carRepository.findByName("prius")).willReturn(new Car("prius", "hybrid"));

                Car car = carService.getCarDetails("prius");

                assertEquals("prius", car.getName());
                assertEquals("hybrid", car.getType());
            }
        }
        
        @Nested
        @DisplayName("When the car is NOT present")
        class MissingCarTest {
            @Test
            @DisplayName("Then an exception is thrown")
            void getCarDetails_shouldThrowCarNotFoundException() {
                given(carRepository.findByName("someName")).willReturn(null);

                assertThrows(CarNotFoundException.class, () -> carService.getCarDetails("someName"));
            }
        }
    }
}