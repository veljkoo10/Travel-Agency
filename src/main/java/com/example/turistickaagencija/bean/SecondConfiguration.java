package com.example.turistickaagencija.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class SecondConfiguration {
    @Bean(name= {"memorijaAplikacije"},
            initMethod="init", destroyMethod="destroy")
    public ApplicationMemory getApplicationMemory() {
        return new ApplicationMemory();
    }

    public class ApplicationMemory extends HashMap {

        @Override
        public String toString() {
            return "ApplicationMemory"+this.hashCode();
        }

        public void init() {
            //inicijalizacija
            System.out.println("init method called");
        }

        public void destroy() {
            //brisanje
            System.out.println("destroy method called");
        }
    }
}
