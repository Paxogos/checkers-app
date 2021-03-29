package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;

public class PostCheckTurnRoute implements Route {

    private final Gson gson;

    public PostCheckTurnRoute(Gson gson) {
        this.gson = Objects.requireNonNull(gson, "Gson must not be null.");
    }

    public Object handle(Request request, Response response) {

        Session httpSession = request.session();

        Player currentUser = httpSession.attribute(GetHomeRoute.PLAYER_ATTR);
        Game currentGame = httpSession.attribute(GetGameRoute.GAME_ATTR);


        boolean isPlayersTurn = currentGame.isPlayersTurn(currentUser);

        if (isPlayersTurn)
            return this.gson.toJson(Message.info("true"));
        else
            return this.gson.toJson(Message.info("false"));


    }
}
