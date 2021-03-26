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
import java.util.Set;

public class PostCheckTurnRoute implements Route {

    private final Gson gson;

    static final String PLAYER_ATTR = "currentUser";
    static final String GAME_ATTR = "currentGame";



    public PostCheckTurnRoute(Gson gson) {
        this.gson = Objects.requireNonNull(gson, "Gson must not be null.");
    }

    public Object handle(Request request, Response response) {

        Session httpSession = request.session();

        Player currentUser = httpSession.attribute(PLAYER_ATTR);


        if (httpSession.attribute(GAME_ATTR) != null) {
            Game currentGame = httpSession.attribute(GetGameRoute.GAME_ATTR);
            Set<String> JSONasString = request.queryParams();
            System.out.println(JSONasString);
            Boolean isPlayersTurn = currentGame.isPlayersTurn(currentUser);
            System.out.println(currentUser);
            System.out.println(isPlayersTurn);

            return gson.toJson(Message.info(isPlayersTurn.toString()));
        }

        return gson.toJson(Message.error("Invalid Session"));


    }
}
