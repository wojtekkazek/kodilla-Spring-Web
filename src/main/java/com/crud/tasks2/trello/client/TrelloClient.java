package com.crud.tasks2.trello.client;

import com.crud.tasks2.controller.ListNotFoundException;
import com.crud.tasks2.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    private URI buildUrl() {
        return  UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();
    }

    public Optional<List<TrelloBoardDto>> getTrelloBoards() {

//        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
//                trelloApiEndpoint + "/members/wojciechkazek/boards" + "?key=" + trelloAppKey + "&token=" + trelloToken,
//                TrelloBoardDto[].class);

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildUrl(), TrelloBoardDto[].class);

        return Arrays.asList(boardsResponse);

    }

}