package be.dog.d.steven.springboottdd1;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Nested
    @DisplayName("Given a car")
    class CarTest {
        @Nested
        @DisplayName("When the car exists")
        class ExistingCarTest {
            @Test
            @DisplayName("Then we get the car")
            void getCar_shouldReturnCar() throws Exception {
                given(carService.getCarDetails(anyString())).willReturn(new Car("prius", "hybrid"));

                mockMvc.perform(get("/cars/prius"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("name").value("prius"))
                        .andExpect(jsonPath("type").value("hybrid"));
            }
        }

        @Nested
        @DisplayName("When the car does NOT exist")
        class MissingCarTest {
            @Test
            @DisplayName("Then we get a 404")
            void getCar_shouldReturnNotFound() throws Exception {
                given(carService.getCarDetails(anyString())).willThrow(new CarNotFoundException("someName"));

                mockMvc.perform(get("/cars/leaf")).andExpect(status().isNotFound());
            }
        }
    }
}
