package com.kodilla.tasks.trello.validator;

import com.kodilla.tasks.domain.TrelloBoard;
import com.kodilla.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloValidatorTest {

    private final TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    void validateIncorrectCardTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test", "this is a test card", "1", "1");
        //When & Then
        trelloValidator.validateCard(trelloCard);
        //Brak asercji, sprawdzam wiadomość w logu.
    }

    @Test
    void validateCorrectCartTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Trello Card", "this is a trello card", "1", "1");
        //When & Then
        trelloValidator.validateCard(trelloCard);
        //Brak asercji, sprawdzam wiadomość w logu.
    }

    @Test
    void validateIncorrectTrelloBoardsTest() {
        //Given
        TrelloBoard board1 = new TrelloBoard("1", "Test", null);
        TrelloBoard board2 = new TrelloBoard("2", "Trello Board", null);
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(board1);
        boardList.add(board2);
        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(boardList);
        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertEquals("Trello Board", resultList.get(0).getName());
    }

    @Test
    void validateCorrectTrelloBoardsTest() {
        //Given
        TrelloBoard board1 = new TrelloBoard("1", "Board", null);
        TrelloBoard board2 = new TrelloBoard("2", "Trello Board", null);
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(board1);
        boardList.add(board2);
        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(boardList);
        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals("Board", resultList.get(0).getName());
    }
}