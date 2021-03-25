package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;

public class PostSubmitTurnRoute implements Route {

    private final Gson gson;

    public PostSubmitTurnRoute(Gson gson) {
        this.gson = Objects.requireNonNull(gson, "Gson must not be null.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        Session httpSession = request.session();
        Game currentGame = httpSession.attribute(GetGameRoute.GAME_ATTR);

        currentGame.toggleActivePlayer();

        return this.gson.toJson(Message.info("Active player has switched."));

    }
}
