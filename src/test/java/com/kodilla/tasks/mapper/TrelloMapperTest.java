package com.kodilla.tasks.mapper;

import com.kodilla.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToBoardsTest() {
        //Given
        List<TrelloListDto> trelloListsDto = List.of(new TrelloListDto("1", "testList", false));
        List<TrelloBoardDto> trelloBoardsDto = List.of(new TrelloBoardDto("1", "testBoard", trelloListsDto));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertNotNull(trelloBoards);
        assertEquals(1, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("testBoard", trelloBoards.get(0).getName());
        assertEquals(1, trelloBoards.get(0).getLists().size());
        assertEquals("testList", trelloBoards.get(0).getLists().get(0).getName());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "testList", false));
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1", "testBoard", trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertNotNull(trelloBoardsDto);
        assertEquals(1, trelloBoardsDto.size());
        assertEquals("1", trelloBoardsDto.get(0).getId());
        assertEquals("testBoard", trelloBoardsDto.get(0).getName());
        assertEquals(1, trelloBoardsDto.get(0).getLists().size());
        assertEquals("testList", trelloBoardsDto.get(0).getLists().get(0).getName());
    }

    @Test
    void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListsDto = List.of(new TrelloListDto("1", "testList", false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertNotNull(trelloLists);
        assertEquals(1, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("testList", trelloLists.get(0).getName());
        assertFalse(trelloLists.get(0).isClosed());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "testList", false));
        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertNotNull(trelloListsDto);
        assertEquals(1, trelloListsDto.size());
        assertEquals("1", trelloListsDto.get(0).getId());
        assertEquals("testList", trelloListsDto.get(0).getName());
        assertFalse(trelloListsDto.get(0).isClosed());
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("testCard", "This is a test Card", "1", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("testCard", trelloCardDto.getName());
        assertEquals("This is a test Card", trelloCardDto.getDescription());
        assertEquals("1", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("testCard", "This is a test Card", "1", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("testCard", trelloCard.getName());
        assertEquals("This is a test Card", trelloCard.getDescription());
        assertEquals("1", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}