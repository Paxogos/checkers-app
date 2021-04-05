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

public class PostResignRoute implements Route {

    private final Gson gson;
    public PostResignRoute(Gson gson) {
        this.gson = Objects.requireNonNull(gson, "Gson must not be null.");
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session httpSession = request.session();

        Game currentGame = httpSession.attribute(GetGameRoute.GAME_ATTR);
        Player currentUser = httpSession.attribute(GetGameRoute.PLAYER_ATTR);

        if (currentGame != null) {
            currentGame.resignGame(currentUser);
            Piece.Color resigningColor = currentGame.getActiveColor();

            return this.gson.toJson(Message.info(currentUser.getName() + " has resigned."));
        }
        else {
            response.redirect(WebServer.GAME_URL);
            return this.gson.toJson(Message.error("Current game could not be found :("));
        }
    }
}
