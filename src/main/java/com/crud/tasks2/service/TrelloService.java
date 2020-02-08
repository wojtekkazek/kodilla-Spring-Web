package com.crud.tasks2.service;

import com.crud.tasks2.config.AdminConfig;
import com.crud.tasks2.domain.Mail;
import com.crud.tasks2.domain.TrelloBoardDto;
import com.crud.tasks2.domain.TrelloCardDto;
import com.crud.tasks2.domain.CreatedTrelloCardDto;
import com.crud.tasks2.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello Card";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "New card: " + card.getName() + " has been created on your Trello account" + "sprawdzenie czy sie wysle",
                null)));
        return newCard;
    }
}
