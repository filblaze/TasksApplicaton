package com.kodilla.tasks.service;

import com.kodilla.tasks.config.AdminConfig;
import com.kodilla.tasks.domain.TrelloBoardDto;
import com.kodilla.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    void fetchEmptyTrelloBoardsTest() {
        //Given
        List<TrelloBoardDto> boardList = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(boardList);
        //When
        List<TrelloBoardDto> resultList = trelloService.fetchTrelloBoards();
        //Then
        assertNotNull(resultList);
        assertEquals(0, resultList.size());
        verify(trelloClient, times(1)).getTrelloBoards();
    }

    @Test
    void fetchTrelloBoardsTest() {
        //Given
        List<TrelloBoardDto> boardList = List.of(new TrelloBoardDto("1", "Trello Board", null));
        when(trelloClient.getTrelloBoards()).thenReturn(boardList);
        //When
        List<TrelloBoardDto> resultList = trelloService.fetchTrelloBoards();
        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertEquals("1", resultList.get(0).getId());
        assertEquals("Trello Board", resultList.get(0).getName());
        verify(trelloClient, times(1)).getTrelloBoards();
    }
}