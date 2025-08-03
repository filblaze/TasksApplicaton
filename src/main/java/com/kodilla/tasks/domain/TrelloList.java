package com.kodilla.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloList {

    private String id;
    private String name;
    private boolean isClosed;
}
