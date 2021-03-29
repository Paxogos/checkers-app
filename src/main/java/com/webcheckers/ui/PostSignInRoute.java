package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Objects;

import static spark.Spark.halt;

public class PostSignInRoute implements Route {

    // Constants
    final String USERNAME_PARAM = "userName";
    final Message NAME_TAKEN = Message.info("Sorry, that name is already taken. Try another!");
    final Message INVALID = Message.info("Name must be between 4 and 11 characters long, " +
            "must begin with a capital letter, must include at least one number, and cannot " +
            "include any symbols.");

    final String MESSAGE_ATTR = "message";
    final String PLAYER_ATTR = "currentUser";

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    public PostSignInRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        final Session httpSession = request.session();
        HashMap<String, Object> vm = new HashMap<>();
        final String name = request.queryParams(USERNAME_PARAM);

        // Checks if the user is coming from a new browser
        if (httpSession.attribute(PLAYER_ATTR) == null) {

            // Attempt to sign in
            PlayerLobby.LoginAttempt attempt = playerLobby.signIn(name);

            // If the name is taken
            if (attempt == PlayerLobby.LoginAttempt.NAME_TAKEN) {
                return templateEngine.render(GetSignInRoute.getSignInPage(NAME_TAKEN));
            }
            else if (attempt == PlayerLobby.LoginAttempt.INVALID){
                return templateEngine.render(GetSignInRoute.getSignInPage(INVALID));
            }
            else {
                Player currentUser = playerLobby.getPlayer(name);
                httpSession.attribute(PLAYER_ATTR, currentUser);
                return templateEngine.render(GetHomeRoute.getHomePage(currentUser, playerLobby, null));
            }

        }

        else {
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }
    }
}
