package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Game.MoveResult;
import com.webcheckers.util.Message;
import com.webcheckers.util.Position;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostValidateMoveRoute implements Route {

    private Gson gson;

    private final String ACTION_DATA_ATTR = "actionData";

    public PostValidateMoveRoute(Gson gson) {
        this.gson = gson;
    }


    public Object handle(Request request, Response response) {

        Session httpSession = request.session();
        Game currentGame;

        if (httpSession.attribute(GetGameRoute.GAME_ATTR) != null) {

            currentGame = httpSession.attribute(GetGameRoute.GAME_ATTR);

            String JSONasString = request.queryParams(ACTION_DATA_ATTR);
            System.out.println(JSONasString);
            Move attemptedMove = this.gson.fromJson(JSONasString, Move.class);
            System.out.println(attemptedMove);

            MoveResult result = currentGame.makeMove(attemptedMove);


            Message returnMessage;
            switch (result) {

                case SIMPLE_MOVE:
                    returnMessage = Message.info("Piece moved from " + attemptedMove.start() + " to " + attemptedMove.end());
                    break;

                case JUMP:
                    returnMessage = Message.info("Piece jumped from " + attemptedMove.start() + " to " + attemptedMove.end());
                    break;

                case OCCUPIED:
                    returnMessage = Message.error("The space " + attemptedMove.end() + " is occupied");
                    break;

                case SINGLE_RESTRICTED:
                    returnMessage = Message.error("Single pieces can only move diagonally forward by one space");
                    break;

                case KING_RESTRICTED:
                    returnMessage = Message.error("King pieces can only move diagonally by one space");
                    break;

                case INVALID:
                    returnMessage = Message.error("There is no piece at " + attemptedMove.start());
                    break;

                default:
                    returnMessage = Message.error("Something went wrong, but we're not sure what.");
            }
        return gson.toJson(returnMessage);

        } else {
            response.redirect(WebServer.HOME_URL);
        }

        return null;
    }

}
