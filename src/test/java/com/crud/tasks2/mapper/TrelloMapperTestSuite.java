package com.crud.tasks2.mapper;

import com.crud.tasks2.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTestSuite {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void shouldMapToBoards () {
        //Given
        List<TrelloListDto> trelloListDto1 = new ArrayList<>();
        trelloListDto1.add(new TrelloListDto("1", "list1", false));
        trelloListDto1.add(new TrelloListDto("2", "list2", false));
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("id1", "board1", trelloListDto1);

        List<TrelloListDto> trelloListDto2 = new ArrayList<>();
        trelloListDto2.add(new TrelloListDto("3", "list3", false));
        trelloListDto2.add(new TrelloListDto("4", "list4", false));
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("id2", "board2", trelloListDto2);

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        Assert.assertEquals(2, trelloBoards.size());
        Assert.assertEquals("list4", trelloBoards.get(1).getLists().get(1).getName());
    }
    
    @Test
    public void shouldMapToBoardsDto () {
        //Given
        List<TrelloList> trelloList1 = new ArrayList<>();
        trelloList1.add(new TrelloList("1", "list1", false));
        trelloList1.add(new TrelloList("2", "list2", false));
        TrelloBoard trelloBoard1 = new TrelloBoard("id1", "board1", trelloList1);

        List<TrelloList> trelloList2 = new ArrayList<>();
        trelloList2.add(new TrelloList("3", "list3", false));
        trelloList2.add(new TrelloList("4", "list4", false));
        TrelloBoard trelloBoard2 = new TrelloBoard("id1", "board1", trelloList2);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals(2, trelloBoardDtos.size());
        Assert.assertEquals("list4", trelloBoardDtos.get(1).getLists().get(1).getName());
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloListDto> trelloListDto1 = new ArrayList<>();
        trelloListDto1.add(new TrelloListDto("1", "list1", false));
        trelloListDto1.add(new TrelloListDto("2", "list2", false));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDto1);

        //Then
        Assert.assertEquals(2, trelloLists.size());
        Assert.assertEquals("list2", trelloLists.get(1).getName());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> trelloList1 = new ArrayList<>();
        trelloList1.add(new TrelloList("1", "list1", false));
        trelloList1.add(new TrelloList("2", "list2", false));

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloList1);

        //Then
        Assert.assertEquals(2, trelloListDtos.size());
        Assert.assertEquals("list2", trelloListDtos.get(1).getName());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "description", "pos", "id");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("description", trelloCardDto.getDescription());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "description", "pos", "id");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals("description", trelloCard.getDescription());
    }

}
