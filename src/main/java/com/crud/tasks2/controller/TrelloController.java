package com.crud.tasks2.controller;

import com.crud.tasks2.domain.CreatedTrelloCardDto;
import com.crud.tasks2.domain.TrelloBoardDto;
import com.crud.tasks2.domain.TrelloCardDto;
import com.crud.tasks2.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();

        //22.2
//        trelloBoards.stream()
//                .filter(board -> board.getId() != null)
//                .filter(board -> board.getName() != null)
//                .filter(board -> board.getName().contains("Kodilla"))
//                .forEach(board -> System.out.println(board.getId() + " " + board.getName()));
    }

    //22.3 - getting ids for lists on Trello
//    @Autowired
//    private TrelloClient trelloClient;
//
//    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
//    public void getTrelloBoards() {
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//        trelloBoards.forEach(trelloBoardDto -> {
//
//            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
//
//            System.out.println("This board contains lists: ");
//
//            trelloBoardDto.getLists().forEach(trelloList ->
//                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//
//        });
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/cards")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }
}