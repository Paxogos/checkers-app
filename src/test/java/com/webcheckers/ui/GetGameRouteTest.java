package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
class GetGameRouteTest {

    private GetGameRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private Player currentUser;
    private Player opponent;

    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();

        playerLobby.signIn("Test1");
        playerLobby.signIn("TestOpp1");
        currentUser = playerLobby.getPlayer("Test1");
        opponent = playerLobby.getPlayer("TestOpp1");

        session.attribute("currentUser",currentUser);
        when(request.session()).thenReturn(session);

        //create gson local variable
        Gson gson = new Gson();

        // create a unique CuT for each test
        CuT = new GetGameRoute(playerLobby, gameCenter, engine, gson);
    }

    @Test
    public void new_game() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute("currentUser")).thenReturn(currentUser);
        when(request.queryString()).thenReturn("TestOpp1");

        try{
            CuT.handle(request,response);
        }catch (spark.HaltException e){
            System.out.println(e + ": GetGameRoute halted, this is the expected behavior of this test");
        }
        // Analyze the content passed into the render method
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data

        //   * test view name
        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }

    @Test
    void game_exists(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute("currentUser")).thenReturn(currentUser);

        Game currentGame = gameCenter.getGame(currentUser,opponent,0);

        try{
            CuT.handle(request,response);
        }catch (spark.HaltException e){
            System.out.println(e + ": GetGameRoute halted, this is the expected behavior of this test");
        }

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data

        //   * test view name
        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }

    @Test
    void not_signed_in(){
        when(session.attribute("currentUser")).thenReturn(null);
        try{
            CuT.handle(request,response);
        }catch (spark.HaltException e){
            System.out.println(e + ": GetGameRoute halted, this is the expected behavior of this test");
        }
    }

    @Test
    void no_opponent_given(){
        when(session.attribute("currentUser")).thenReturn(currentUser);
        when(request.queryString()).thenReturn(null);
        try{
            CuT.handle(request,response);
        }catch (spark.HaltException e){
            System.out.println(e + ": GetGameRoute halted, this is the expected behavior of this test");
        }
    }
}

