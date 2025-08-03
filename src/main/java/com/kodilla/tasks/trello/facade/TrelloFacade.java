package com.kodilla.tasks.trello.facade;

import com.kodilla.tasks.domain.*;
import com.kodilla.tasks.mapper.TrelloMapper;
import com.kodilla.tasks.service.TrelloService;
import com.kodilla.tasks.trello.validator.TrelloValidator;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrelloFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    private final TrelloService trelloService;
    private final TrelloMapper trelloMapper;
    private final TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }

}
