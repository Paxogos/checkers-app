package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
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

        engineTester.assertViewModelAttribute(GetHomeRoute.PLAYER_ATTR, GetHomeRoute.AVAILABLE_PLAYER_LIST_ATTR);
        engineTester.assertViewModelAttribute(GetHomeRoute.LOBBY_SIZE_ATTR, GetHomeRoute.BUSY_PLAYER_LIST_ATTR);

    }

}
