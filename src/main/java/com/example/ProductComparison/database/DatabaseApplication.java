package com.example.ProductComparison.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


public class DatabaseApplication {
    private static final Logger log = LoggerFactory.getLogger(DatabaseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new ProductHistory(1,50.0, "vanilla cake", "chocolate cake", "flour", "vanilla", "chocolate"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (ProductHistory productHistory : repository.findAll()) {
                log.info(productHistory.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Optional<ProductHistory> user = repository.findById(1);
            log.info("Customer found with findById(1):");
            log.info("--------------------------------");
            log.info(user.toString());
            log.info("");


        };
    }

}
