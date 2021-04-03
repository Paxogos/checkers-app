package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.HashMap;
import java.util.Map;
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

        boolean pieceCanJump = false;

        if (httpSession.attribute(GetGameRoute.GAME_ATTR) != null) {
            currentGame = httpSession.attribute(GetGameRoute.GAME_ATTR);
            Set<String> JSONasString = request.queryParams();

            if(currentGame.isGameOver()){

            }
            pieceCanJump = currentGame.canPlayJumpMove();
            currentGame.completeTurn();

        }


        if (pieceCanJump)
            return gson.toJson(Message.error("A piece can still be moved"));
        else
            return gson.toJson(Message.info("Active player has been switched"));
    }

}
