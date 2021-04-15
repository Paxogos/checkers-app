package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;

public class PostBackupMoveRoute implements Route {

    private Gson gson;
    private GameCenter gameCenter;

    /**
     * Route controller for backing up a move
     *
     * @param gameCenter   GameCenter object of the WebServer
     * @param gson    Gson object used for converting JavaScript files
     */
    public PostBackupMoveRoute(Gson gson, GameCenter gameCenter) {
        this.gson = Objects.requireNonNull(gson, "Gson must not be null.");
        this.gameCenter = gameCenter;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        Session httpSession = request.session();

        Game currentGame = gameCenter.getGame(Integer.parseInt(request.queryParams("gameID")));

        boolean successfulBackup = currentGame.backup();

        if (successfulBackup)
            return this.gson.toJson(Message.info("Turn backed up."));
        else
            return this.gson.toJson(Message.error("No turn to backup from."));

    }
}
