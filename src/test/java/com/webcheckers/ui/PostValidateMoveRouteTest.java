/*
package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.*;
import com.webcheckers.ui.PostValidateMoveRoute;
import com.webcheckers.util.Message;
import com.webcheckers.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostValidateMoveRouteTest {

    private PostValidateMoveRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private Game currentGame;
    private Player currentUser;
    private Player opponent;
    private Move move;
    private Gson gson;


    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        move = mock(Move.class);

        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();

        playerLobby.signIn("Test1");
        playerLobby.signIn("Test2");
        currentUser = playerLobby.getPlayer("Test1");
        opponent = playerLobby.getPlayer("Test2");
        currentGame = gameCenter.getGame(currentUser,opponent,0);
        gson = new Gson();

        when(request.session()).thenReturn(session);
        when(session.attribute("currentGame")).thenReturn(currentGame);

        CuT = new PostValidateMoveRoute(gson,gameCenter);
    }

    @Test
    void simple_move_red(){
        Position simpleStart = new Position(5,0);
        Position simpleEnd = new Position(4,1);

        when(request.queryParams("actionData")).thenReturn(gson.toJson(new Move(simpleStart,simpleEnd)));

        assertEquals(gson.toJson(Message.info("Piece moved from " + simpleStart + " to " + simpleEnd)),
                CuT.handle(request,response));

    }

    @Test
    void jump_move_red(){
        Position simpleStart = new Position(5,0);
        Position midPoint = new Position(4,1);
        Position simpleEnd = new Position(3,2);
        currentGame.getBoard().setSpaceToPiece(midPoint,new Single(Piece.Color.WHITE));

        when(request.queryParams("actionData")).thenReturn(gson.toJson(new Move(simpleStart,simpleEnd)));

        assertEquals(gson.toJson(Message.info("Piece jumped from " + simpleStart + " to " + simpleEnd)),
                CuT.handle(request,response));
    }

    @Test
    void non_continuous_move_red(){
        Position simpleStart1 = new Position(5,0);
        Position midPoint1 = new Position(4,1);
        Position simpleEnd1 = new Position(3,2);
        currentGame.getBoard().setSpaceToPiece(midPoint1,new Single(Piece.Color.WHITE));

        when(request.queryParams("actionData")).thenReturn(gson.toJson(new Move(simpleStart1,simpleEnd1)));

        assertEquals(gson.toJson(Message.info("Piece jumped from " + simpleStart1 + " to " + simpleEnd1)),
                CuT.handle(request,response));

    }

}

 */