package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.*;
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

    // These are test values!! Do not leave them without verifying they belong!!

    static final String TITLE_ATTR = "title";
    static final String GAME_ID_ATTR = "gameID";
    static final String CURRENT_USER_ATTR = "currentUser";
    static final String VIEW_MODE_ATTR = "viewMode";
    static final String MODE_OPTIONS_ATTR = "modeOptionsAsJSON";
    static final String RED_PLAYER_ATTR = "redPlayer";
    static final String WHITE_PLAYER_ATTR = "whitePlayer";
    static final String ACTIVE_COLOR_ATTR = "activeColor";
    static final String BOARD_ATTR = "board";
    static final String PLAYER_ATTR = "currentUser";

    static final String GAME_ATTR = "currentGame";


    static String TITLE = "Game";
    static String VIEW_NAME = "game.ftl";
    static String GAME_ID = "12345";
    static String VIEW_MODE = "PLAY";
    static String MODE_OPTIONS = "{}";

    private PlayerLobby playerLobby;
    private GameCenter gameCenter;

    // Values used in the view-model map for rendering the game view.
    private final TemplateEngine templateEngine;

    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(PlayerLobby playerLobby, GameCenter gameCenter, final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String handle(Request request, Response response) {

        // retrieve the game object and start one if no game is in progress
        final Session httpSession = request.session();
        if(httpSession.attribute(PLAYER_ATTR) == null){
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }

        Player currentUser = httpSession.attribute(PLAYER_ATTR);
        Player opponent = gameCenter.getCurrentOpponent(currentUser);

        // If the currentUser does not have an existing game and there is not opponent in params, return to HOME_URL
        Iterator<String> paramIterator = request.queryParams().iterator();
        if(opponent == null && !paramIterator.hasNext()){
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }



        // build the View-Model
        final Map<String, Object> vm = new HashMap<>();
        Game currentGame;
        BoardView boardview;

        // game exists, retrieve game and render FTL accordingly
        if(opponent != null){
            currentGame = gameCenter.getGame(currentUser,opponent);
            vm.put(RED_PLAYER_ATTR,currentGame.getRedPlayer());
            vm.put(WHITE_PLAYER_ATTR,currentGame.getWhitePlayer());
            vm.put(ACTIVE_COLOR_ATTR, currentGame.getActiveColor());


            if(currentUser == currentGame.getRedPlayer()){
                boardview = gameCenter.getBoardForRed(currentGame);
            }else{
                boardview = gameCenter.getBoardForWhite(currentGame);
            }
            vm.put(BOARD_ATTR, boardview);


        }else{ // create a new game, currentUser must be red
            opponent = playerLobby.getPlayer(paramIterator.next());

            // if the selected opponent is already in a game
            if (playerLobby.isPlayerInGame(opponent)) {
                response.redirect("/");
                halt();
                return null;
            }

            currentGame = gameCenter.getGame(currentUser, opponent);
            vm.put(RED_PLAYER_ATTR, currentUser);
            vm.put(WHITE_PLAYER_ATTR, opponent);
            vm.put(ACTIVE_COLOR_ATTR, Piece.Color.RED);
            vm.put(BOARD_ATTR, gameCenter.getBoardForRed(currentGame));
        }

        httpSession.attribute(GAME_ATTR, currentGame);
        playerLobby.setPlayerBusy(currentUser);
        playerLobby.setPlayerBusy(opponent);

        vm.put(TITLE_ATTR, TITLE);
        vm.put(CURRENT_USER_ATTR, currentUser);
        vm.put(VIEW_MODE_ATTR, VIEW_MODE);
        vm.put(MODE_OPTIONS_ATTR, MODE_OPTIONS);
        vm.put(GAME_ID_ATTR, GAME_ID);

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));

    }

}