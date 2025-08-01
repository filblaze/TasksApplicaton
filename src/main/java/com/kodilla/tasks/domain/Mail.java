package com.kodilla.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Mail {

    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCC;
}
