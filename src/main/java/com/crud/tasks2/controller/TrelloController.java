package com.crud.tasks2.controller;

import com.crud.tasks2.domain.TrelloBoardDto;
import com.crud.tasks2.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.stream()
                .filter(board -> board.getId() != null)
                .filter(board -> board.getName() != null)
                .filter(board -> board.getName().contains("Kodilla"))
                .forEach(board -> System.out.println(board.getId() + " " + board.getName()));

    }
}
