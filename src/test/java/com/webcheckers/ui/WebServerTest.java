package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WebServerTest {

    WebServer CuT;

    @BeforeEach
    void ctor(){
        PlayerLobby playerLobby = new PlayerLobby();
        GameCenter gameCenter = new GameCenter();
        TemplateEngine engine = mock(TemplateEngine.class);
        Gson gson = new Gson();
        CuT = new WebServer(engine,gson,playerLobby,gameCenter);
    }

    @Test
    void init(){
        CuT.initialize();
    }

}