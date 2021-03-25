package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.List;
import java.util.Set;

public class PostSubmitTurnRoute implements Route {

    private Gson gson;

    private final String ACTION_DATA_ATTR = "actionData";

    public PostSubmitTurnRoute(Gson gson) {
        this.gson = gson;
    }


    public Object handle(Request request, Response response) {

        Session httpSession = request.session();
        Game currentGame;

        if (httpSession.attribute(GetGameRoute.GAME_ATTR) != null) {
            currentGame = httpSession.attribute(GetGameRoute.GAME_ATTR);
            Set<String> JSONasString = request.queryParams();
            System.out.println(JSONasString);

            currentGame.toggleActivePlayer();
            currentGame.completeTurn();

        }


        return gson.toJson(Message.info("Test"));
    }

}
