package com.kodilla.tasks.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="NAME")
    private String title;

    @Column(name="DESCRIPTION")
    private String content;
}
