package me.pro0xy.bankemulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAsync
public class BankEmulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankEmulationApplication.class, args);
    }

}
