package com.kodilla.tasks.controller;

import com.kodilla.tasks.domain.CreatedTrelloCardDto;
import com.kodilla.tasks.domain.TrelloBoardDto;
import com.kodilla.tasks.domain.TrelloCardDto;
import com.kodilla.tasks.service.TrelloService;
import com.kodilla.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloFacade trelloFacade;

    @GetMapping("boards")
    public ResponseEntity<List<TrelloBoardDto>> getTrelloBoards() {
        return ResponseEntity.ok(trelloFacade.fetchTrelloBoards());

        /*List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.stream()
                .filter(n -> Objects.nonNull(n.getId()) && Objects.nonNull(n.getName()) && n.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
        });*/
    }

    @PostMapping("cards")
    public ResponseEntity<CreatedTrelloCardDto> createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return ResponseEntity.ok(trelloFacade.createCard(trelloCardDto));
    }
}
