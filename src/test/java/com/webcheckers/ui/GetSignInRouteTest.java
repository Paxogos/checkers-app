package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Ui-tier")
class GetSignInRouteTest {

    private GetSignInRoute CuT;

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

        when(request.session()).thenReturn(session);

        CuT = new GetSignInRoute(engine);
    }

    @Test
    public void not_signed_in() throws Exception {
        CuT.handle(request,response);
    }

    @Test
    public void signed_in() throws Exception {
        currentUser = new Player("SignedIn1");
        when(session.attribute("currentUser")).thenReturn(currentUser);
        try{
            CuT.handle(request,response);
        }catch (spark.HaltException e){
            System.out.println(e + ": GetSignIn halted, this is the expected behavior of this test");
        }

    }



}