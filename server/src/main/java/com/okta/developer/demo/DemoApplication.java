package com.okta.developer.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.stream.Stream;

@EnableResourceServer
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CarRepository repository) {        
        return args -> {
            Car car1 = new Car();
            car1.setName("Ferrari");
            car1.setTitle("Test");
            car1.setCategory("Health");
            car1.setDescription("Work out every day");
            car1.setDueDate("28 February 2019");
            car1.setComplete(false);
            repository.save(car1);
            Car car2 = new Car();
            car2.setName("Ferrari");
            car2.setTitle("Test");
            car2.setCategory("Health");
            car2.setDescription("Work out every day");
            car2.setDueDate("28 February 2019");
            car2.setComplete(true);
            repository.save(car2);
            Car car3 = new Car();
            car3.setName("Ferrari");
            car3.setTitle("Test");
            car3.setCategory("Health");
            car3.setDescription("Work out every day");
            car3.setDueDate("28 February 2019");
            car3.setComplete(true);
            repository.save(car3);
            repository.findAll().forEach(System.out::println);
        };
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
