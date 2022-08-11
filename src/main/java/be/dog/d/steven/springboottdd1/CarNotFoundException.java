package be.dog.d.steven.springboottdd1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String name) {
        log.info("Car with name: {}, was not found.", name);
    }
}