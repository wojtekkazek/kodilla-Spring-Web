package com.crud.tasks2.domain;

import com.crud.tasks2.domain.Badges;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCardDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

//    @JsonProperty("votes")
//    private int votes;
//
//    @JsonProperty("board")
//    private int board;
//
//    @JsonProperty("card")
//    private int card;

}