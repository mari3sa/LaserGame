package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableJpaRepositories(basePackages = "LaserGame.Repository")
@EntityScan(basePackages = "LaserGame.Entities")
@SpringBootApplication
public class LaserGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(LaserGameApplication.class, args);
    }
}
