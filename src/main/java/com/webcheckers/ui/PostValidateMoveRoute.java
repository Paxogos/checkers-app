package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Game.MoveResult;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostValidateMoveRoute implements Route {

    private GameCenter gameCenter;
    private Gson gson;

    public PostValidateMoveRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = gameCenter;
        this.gson = gson;
    }


    public Object handle(Request request, Response response) {

        Session httpSession = request.session();
        Game currentGame;

        if (httpSession.attribute(GetGameRoute.GAME_ATTR) != null) {

            currentGame = httpSession.attribute(GetGameRoute.GAME_ATTR);
            String JSONasString = request.body();
            Move attemptedMove = this.gson.fromJson(JSONasString, Move.class);

            MoveResult result = currentGame.makeMove(attemptedMove);


            switch (result) {

                case SIMPLE_MOVE:
                    return Message.info("Piece moved from " + attemptedMove.start() + " to " + attemptedMove.end());

                case JUMP:
                    return Message.info("Piece jumped from " + attemptedMove.start() + " to " + attemptedMove.end());

                case OCCUPIED:
                    return Message.error("The space " + attemptedMove.end() + " is occupied");

                case SINGLE_RESTRICTED:
                    return Message.error("Single pieces can only move diagonally forward by one space");

                case KING_RESTRICTED:
                    return Message.error("King pieces can only move diagonally by one space");

                case INVALID:
                    return Message.error("There is no piece at " + attemptedMove.start());
            }


        } else {
            response.redirect(WebServer.HOME_URL);
        }

        return null;
    }

}
