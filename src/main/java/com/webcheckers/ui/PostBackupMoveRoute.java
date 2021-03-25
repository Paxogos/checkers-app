package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;

public class PostBackupMoveRoute implements Route {

    private Gson gson;

    public PostBackupMoveRoute(Gson gson) {
        this.gson = Objects.requireNonNull(gson, "Gson must not be null.");
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        Session httpSession = request.session();

        Game currentGame = httpSession.attribute(GetGameRoute.GAME_ATTR);

        boolean successfulBackup = currentGame.backup();

        if (successfulBackup)
            return this.gson.toJson(Message.info("Turn backed up."));
        else
            return this.gson.toJson(Message.error("No turn to backup from."));

    }
}
