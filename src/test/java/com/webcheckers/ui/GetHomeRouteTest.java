package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class GetHomeRouteTest {

    private GetHomeRoute CuT;

    //mock objects to help with tests
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private Player currentUser;
    private Player opponent;

    @BeforeEach
    //Creating mock objects to run with the test
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();

        when(request.session()).thenReturn(session);
        when(session.attribute("currentUser")).thenReturn(currentUser);

        CuT = new GetHomeRoute(playerLobby, gameCenter, engine);
    }

    @Test
    void new_session() {
        final TemplateEngineTester engineTester = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(engineTester.makeAnswer());

        //make the handler CuT to start the test
        CuT.handle(request, response);

        engineTester.assertViewModelExists();
        engineTester.assertViewModelIsaMap();

        //testing the view-model data
        engineTester.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE);
        engineTester.assertViewModelAttribute(GetHomeRoute.WELCOME_MSG_ATTR, GetHomeRoute.WELCOME_MSG);
        //test VIEW_NAME
        engineTester.assertViewName(GetHomeRoute.VIEW_NAME);
    }

    @Test
    void signed_in(){
        final TemplateEngineTester engineTester = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(engineTester.makeAnswer());
        session.attribute("currentUser",currentUser);
        playerLobby.signIn("Test1");
        playerLobby.signIn("TestOpp1");

        currentUser = playerLobby.getPlayer("Test1");
        opponent = playerLobby.getPlayer("TestOpp1");

        Game currentGame = gameCenter.getGame(currentUser,opponent);

        when(session.attribute("currentUser")).thenReturn(currentUser);

        CuT.handle(request,response);

        engineTester.assertViewModelExists();
        engineTester.assertViewModelIsaMap();

    }

    @Test
    void busy_opponent(){
        final TemplateEngineTester engineTester = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(engineTester.makeAnswer());
        session.attribute("currentUser",currentUser);
        playerLobby.signIn("Test1");
        playerLobby.signIn("TestOpp2");
        playerLobby.signIn("TestOpp1");

        currentUser = playerLobby.getPlayer("Test1");
        Player opponent1 = playerLobby.getPlayer("TestOpp1");
        Player opponent2 = playerLobby.getPlayer("TestOpp2");

        Game opponentGame = gameCenter.getGame(opponent1,opponent2);


        when(session.attribute("currentUser")).thenReturn(currentUser);
        when(session.attribute("opponentBusy")).thenReturn("true");

        CuT.handle(request,response);

        engineTester.assertViewModelExists();
        engineTester.assertViewModelIsaMap();

    }




}
