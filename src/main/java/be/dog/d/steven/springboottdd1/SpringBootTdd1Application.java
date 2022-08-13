package be.dog.d.steven.springboottdd1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootTdd1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTdd1Application.class, args);
    }
}
