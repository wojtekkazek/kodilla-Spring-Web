package com.crud.tasks2.trello.client;

import com.crud.tasks2.domain.TrelloBoardDto;
import com.crud.tasks2.domain.TrelloCardDto;
import com.crud.tasks2.domain.CreatedTrelloCardDto;
import com.crud.tasks2.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    private URI buildUrl() {
        return  UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {

//        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
////                trelloApiEndpoint + "/members/wojciechkazek/boards" + "?key=" + trelloAppKey + "&token=" + trelloToken,
////                TrelloBoardDto[].class);

        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildUrl(), TrelloBoardDto[].class);
            return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }



    }

    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto) {

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
//                .queryParam("votes", trelloCardDto.getBadges().getVotes())
//                .queryParam("board", trelloCardDto.getBadges().getAttachments().getTrello().getBoard())
//                .queryParam("card", trelloCardDto.getBadges().getAttachments().getTrello().getCard())
                .build().encode().toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCardDto.class);
    }

}