package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Game.MoveResult;
import com.webcheckers.util.Message;
import com.webcheckers.util.Position;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;

public class PostValidateMoveRoute implements Route {

    private Gson gson;
    private GameCenter gameCenter;

    private final String ACTION_DATA_ATTR = "actionData";

    /**
     * Route controller for validating a move
     *
     * @param gameCenter   GameCenter object of the WebServer
     * @param gson    Gson object used for converting JavaScript files
     */
    public PostValidateMoveRoute(Gson gson, GameCenter gameCenter) {

        this.gson = Objects.requireNonNull(gson, "Gson must not be null.");
        this.gameCenter = gameCenter;
    }


    public Object handle(Request request, Response response) {

        Game currentGame;

        if (request.queryParams("gameID") != null) {
            currentGame = gameCenter.getGame(Integer.parseInt(request.queryParams("gameID")));
            String JSONasString = request.queryParams(ACTION_DATA_ATTR);
            System.out.println(request.queryParams());
            System.out.println(JSONasString);
            Move attemptedMove = gson.fromJson(JSONasString, Move.class);
            System.out.println(attemptedMove);

            MoveResult result = currentGame.makeMove(attemptedMove);

            Message returnMessage;
            switch (result) {

                case NON_CONTINUOUS:
                    returnMessage = Message.error("Can only move one piece during your turn.");
                    break;

                case SIMPLE_MOVES_EXCEEDED:
                    returnMessage = Message.error("Cannot make more than one simple move per turn.");
                    break;

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

                case CAN_PLAY_JUMP:
                    returnMessage = Message.error("A jump move can be played");
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
