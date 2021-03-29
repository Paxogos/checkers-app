package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostSignInRouteTest {
    private PostSignInRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private Player currentUser;
    private Player opponent;

    @BeforeEach
    void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        playerLobby = new PlayerLobby();

        when(request.session()).thenReturn(session);

        CuT = new PostSignInRoute(playerLobby,engine);
    }

    @Test
    void taken_username() throws Exception {
        playerLobby.signIn("TakenName1");
        when(request.queryParams("userName")).thenReturn("TakenName1");
        CuT.handle(request,response);
    }

    @Test
    void invalid_username() throws Exception {
        when(request.queryParams("userName")).thenReturn("invalidName");
        CuT.handle(request,response);
    }

    @Test
    void valid_username() throws Exception {
        when(request.queryParams("userName")).thenReturn("ValidName1");
        CuT.handle(request,response);
    }

    @Test
    void logged_in() throws Exception {
        when(session.attribute("currentUser")).thenReturn(new Player("LoggedIn1"));
        try{
            CuT.handle(request,response);
        }catch (spark.HaltException e){
            System.out.println(e + ": PostSignIn halted, this is the expected behavior of this test");
        }
    }

}