package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TrelloConfig {
    @Value("${trello.api.endpoint.prod}")
    String trelloApiEndpoint;
    @Value("${trello.app.key}")
    String trelloAppKey;
    @Value("${trello.app.token}")
    String trelloAppToken;
    @Value("${trello.app.username}")
    String trelloAppUsername;
}
