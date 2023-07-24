package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Important", "Things that must be done", "Center", "CC1-1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Important", trelloCard.getName());
        assertEquals("Things that must be done", trelloCard.getDescription());
        assertEquals("Center", trelloCard.getPos());
        assertEquals("CC1-1", trelloCard.getListId());
    }
    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Important", "Things that must be done", "Center", "CC1-1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Important", trelloCardDto.getName());
        assertEquals("Things that must be done", trelloCardDto.getDescription());
        assertEquals("Center", trelloCardDto.getPos());
        assertEquals("CC1-1", trelloCardDto.getListId());
    }
    @Test
    void mapToTrelloListTest() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("1", "first", true));
        trelloListDtoList.add(new TrelloListDto("2", "second", false));
        trelloListDtoList.add(new TrelloListDto("3", "third", true));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);
        int listSize = trelloLists.size();
        //Then
        assertEquals(3, listSize);
    }
    @Test
    void mapToTrelloListEmptyTest() {
        //Given
        List<TrelloListDto> emptyList = new ArrayList<>();
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(emptyList);
        //Then
        assertTrue(trelloLists.isEmpty());
    }

    @Test
    void mapToTrelloListDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "first", true));
        trelloLists.add(new TrelloList("2", "second", false));
        trelloLists.add(new TrelloList("3", "third", true));
        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);
        int listSize = trelloLists.size();
        //Then
        assertEquals(3, listSize);
    }
    @Test
    void mapToTrelloListDtoEmptyTest() {
        //Given
        List<TrelloList> emptyList = new ArrayList<>();
        //When
        List<TrelloListDto> trelloLists = trelloMapper.mapToListDto(emptyList);
        //Then
        assertTrue(trelloLists.isEmpty());
    }
    @Test
    void mapToTrelloBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("Now", "1", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("Later", "2", Arrays.asList(new TrelloListDto("1", "first", true), new TrelloListDto("2", "second", false))));
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        int listSize = trelloBoardList.size();
        //Then
        assertEquals(2, listSize);
    }
    @Test
    void mapToTrelloBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("Now", "1", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("Later", "2", Arrays.asList(new TrelloList("1", "first", true), new TrelloList("2", "second", false))));
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        int listSize = trelloBoardList.size();
        //Then
        assertEquals(2, listSize);
    }
    @Test
    void mapToBoardEmptyTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        //When
        List<TrelloBoard> emptyList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void mapToBoardDtoEmptyTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        //When
        List<TrelloBoardDto> emptyList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertTrue(emptyList.isEmpty());
    }

}
