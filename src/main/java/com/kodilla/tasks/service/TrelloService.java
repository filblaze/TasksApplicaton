package com.kodilla.tasks.service;

import com.kodilla.tasks.config.AdminConfig;
import com.kodilla.tasks.domain.CreatedTrelloCardDto;
import com.kodilla.tasks.domain.TrelloBoardDto;
import com.kodilla.tasks.domain.TrelloCardDto;
import com.kodilla.tasks.domain.Mail;
import com.kodilla.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class TrelloService {

    public static final String SUBJECT = "Tasks: New Trello card";
    private final TrelloClient trelloClient;
    private final SimpleEmailService emailService;
    private final AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "New card: " + trelloCardDto.getName() + " has been created on your Trello account",
                        null
        )));
        return newCard;
    }
}
