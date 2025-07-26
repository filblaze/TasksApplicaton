package com.kodilla.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class Mail {

    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCC;
}
