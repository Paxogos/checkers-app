package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
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
        Player opponent;
        Player currentUser = httpSession.attribute(PLAYER_ATTR);
        System.out.println("Current User: " + currentUser.getName());
        // build the View-Model
        final Map<String, Object> vm = new HashMap<>();

        Iterator<String> paramIterator = request.queryParams().iterator();
        if (paramIterator.hasNext()) {
            opponent = playerLobby.getPlayer(paramIterator.next());

            if (playerLobby.isPlayerInGame(opponent) && !gameCenter.gameExists(currentUser, opponent)) {
                response.redirect("/");
                halt();
                return null;
            }


            Game currentGame = gameCenter.getGame(currentUser, opponent);

            // BoardView to send to the Browser
            BoardView BOARD;
            if (currentUser.getName().equals(currentGame.getRedPlayer().getName())) {
                vm.put(RED_PLAYER_ATTR, currentUser);
                vm.put(WHITE_PLAYER_ATTR, opponent);
                BOARD = gameCenter.getBoardForRed(currentGame);
            } else {
                vm.put(RED_PLAYER_ATTR, opponent);
                vm.put(WHITE_PLAYER_ATTR, currentUser);
                BOARD = gameCenter.getBoardForWhite(currentGame);
            }

            httpSession.attribute(GAME_ATTR, currentGame);
            playerLobby.setPlayerBusy(currentUser);
            playerLobby.setPlayerBusy(opponent);

            vm.put(TITLE_ATTR, TITLE);
            vm.put(CURRENT_USER_ATTR, currentUser);
            vm.put(VIEW_MODE_ATTR, VIEW_MODE);
            vm.put(MODE_OPTIONS_ATTR, MODE_OPTIONS);
            vm.put(GAME_ID_ATTR, GAME_ID);
            vm.put(ACTIVE_COLOR_ATTR, "RED");
            vm.put(BOARD_ATTR, BOARD);

            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));

        }

        else {
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }
    }

}