package com.crud.tasks2.trello.validator;

import com.crud.tasks2.domain.TrelloBoard;
import com.crud.tasks2.domain.TrelloCard;
import com.crud.tasks2.trello.facade.TrelloFacade;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloValidator {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TrelloValidator.class);

    public void validateCard(final TrelloCard trelloCard) {
        if(trelloCard.getName().contains("test")) {
            LOGGER.info("Someone is testing my application");
        } else {
            LOGGER.info("Seems that my application is used in proper way");
        }
    }

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards) {
        LOGGER.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(trelloBoard -> trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(toList());
        LOGGER.info("Boards have been filtered. Current list size: " + filteredBoards.size());

        return filteredBoards;
    }
}
