package com.okta.developer.demo;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String category;
    private String description;
    private String dueDate;
    private Boolean complete;
    private @NonNull String name;
}