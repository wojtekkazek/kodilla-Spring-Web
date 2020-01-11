package com.crud.tasks2.trello.validator;

import com.crud.tasks2.domain.TrelloBoard;
import com.crud.tasks2.domain.TrelloCard;
import com.crud.tasks2.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloValidatorTestSuite {

    private TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    public void shouldValidateCard() {
       //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listUd");

        //When & Then
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    public void shouldValidateTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "List", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));
        trelloBoards.add(new TrelloBoard("2", "Task", trelloLists));

        //When
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        Assert.assertEquals(1, validatedBoards.size());
    }
}
