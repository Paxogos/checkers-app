package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.gson.Gson;
import com.webcheckers.model.Player;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;

import static spark.Spark.halt;


/**
 * The {@code GET /game} route handler.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 * @author <a href='mailto:jrv@se.rit.edu'>Jim Vallino</a>
 */
public class GetGameRoute implements Route {

    // These are test value!! Do not leave them without verifying they belong!!

    static final String TITLE_ATTR = "title";
    static final String GAME_ID_ATTR = "gameID";
    static final String CURRENT_USER_ATTR = "currentUser";
    static final String VIEW_MODE_ATTR = "viewMode";
    static final String MODE_OPTIONS_ATTR = "modeOptionsAsJSON";
    static final String RED_PLAYER_ATTR = "redPlayer";
    static final String WHITE_PLAYER_ATTR = "whitePlayer";
    static final String ACTIVE_COLOR_ATTR = "activeColor";

    static final String TITLE = "title";
    static final String VIEW_NAME = "game.ftl";
    static final String GAME_ID = "12345";
    static final Player CURRENT_USER = new Player("Aaron", Player.Color.RED);
    static final String VIEW_MODE = "PLAY";
    static final String MODE_OPTIONS = "{}}";
    static final String RED_PLAYER = "Aaron";
    static final String WHITE_PLAYER = "Tyler";
    static final String ACTIVE_COLOR = "RED";

    /*
    window.gameData = {
    "gameID" : ${gameID!'null'},
    "currentUser" : "${currentUser.name}",
    "viewMode" : "${viewMode}",
    "modeOptions" : ${modeOptionsAsJSON!'{}'},
    "redPlayer" : "${redPlayer.name}",
    "whitePlayer" : "${whitePlayer.name}",
    "activeColor" : "${activeColor}"
  };
     */


    // Values used in the view-model map for rendering the game view.

    private final TemplateEngine templateEngine;

    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String handle(Request request, Response response) {

        Gson gson = new Gson();
        // retrieve the game object and start one if no game is in progress
        final Session httpSession = request.session();



        // build the View-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR,TITLE);
        vm.put(CURRENT_USER_ATTR,gson.toJson(CURRENT_USER));
        vm.put(VIEW_MODE_ATTR,VIEW_MODE);
        vm.put(MODE_OPTIONS_ATTR,MODE_OPTIONS);
        vm.put(RED_PLAYER_ATTR,RED_PLAYER);
        vm.put(WHITE_PLAYER_ATTR,WHITE_PLAYER);
        vm.put(GAME_ID_ATTR,GAME_ID);
        vm.put(ACTIVE_COLOR_ATTR,ACTIVE_COLOR);


        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));


    }
}

