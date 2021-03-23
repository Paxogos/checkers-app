package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

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

    @BeforeEach
    //Creating mock objects to run with the test
    public void setup() {
        session = mock(Session.class);
        response = mock(Response.class);
        templateEngine = mock(TemplateEngine.class);

        gameCenter = new GameCenter();
        playerLobby = new PlayerLobby();

        CuT = new GetHomeRoute(playerLobby, gameCenter, templateEngine);
    }

    public void new_session() {
        final TemplateEngineTester engineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(engineTester.makeAnswer());

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

    //@Test
    /*public void old_session() {
        when(session.attribute(gameCenter.getCurrentOpponent(GetHomeRoute.PLAYER_ATTR)).thenReturn(gameCenter.getCurrentOpponent(GetHomeRoute.PLAYER_ATTR)));


        //Invoke test
        try {
            CuT.handle(request, response);
            fail("redirects invoke halt expections");
        } catch (HaltException e) {
            e.getLocalizedMessage();
        }
    }

     */

}
