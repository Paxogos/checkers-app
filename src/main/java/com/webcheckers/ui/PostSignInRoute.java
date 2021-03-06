package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Objects;

public class PostSignInRoute implements Route {

    // Constants
    final String USERNAME_PARAM = "userName";
    final String NAME_TAKEN = "Sorry, that name is already taken. Try another!";

    final String MESSAGE_ATTR = "message";

    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    public PostSignInRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        HashMap<String, Object> vm = new HashMap<>();
        final String name = request.queryParams(USERNAME_PARAM);

        // If name is taken, ask for a different name
        if (playerLobby.hasPlayer(name)) {
            vm.put(MESSAGE_ATTR, NAME_TAKEN);
            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }

        // If name is available
        else {
            Player newUser = new Player(name, Player.Color.NONE);
            // TODO: somehow store the Player data in the session
        }



        return null;
    }
}
