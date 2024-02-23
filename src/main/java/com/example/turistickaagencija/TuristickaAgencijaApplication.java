package com.example.turistickaagencija;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TuristickaAgencijaApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TuristickaAgencijaApplication.class);
    }
    public static void main(String[] args) {
        //SpringApplication.run(TuristickaAgencijaApplication.class, args);
        SpringApplication sa = new SpringApplication(
                TuristickaAgencijaApplication.class);
        sa.run(args);
    }

}
