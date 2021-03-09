package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.BoardView;
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


    static String TITLE = "Game";
    static String VIEW_NAME = "game.ftl";
    static String GAME_ID = "12345";
    static Player CURRENT_USER = new Player("currentUser");
    static String VIEW_MODE = "PLAY";
    static String MODE_OPTIONS = "{}";
    static Player RED_PLAYER = new Player("testPlayer");
    static Player WHITE_PLAYER = new Player("whitePlayer");
    static String ACTIVE_COLOR = "RED";

    // parameters from game.ftl
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

    public static final String PLAYER_ATTR = "currentUser";
    BoardView BOARD;


    private PlayerLobby playerLobby;

    // Values used in the view-model map for rendering the game view.

    private final TemplateEngine templateEngine;

    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(PlayerLobby playerLobby,final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String handle(Request request, Response response) {

        // retrieve the game object and start one if no game is in progress
        final Session httpSession = request.session();
        Player opponent = null;

        Iterator paramIterator = request.queryParams().iterator();
        if(paramIterator.hasNext()){
            opponent = playerLobby.getPlayer((String) paramIterator.next());
        }
        Player currentUser = httpSession.attribute(PLAYER_ATTR);

        CURRENT_USER = currentUser;
        BOARD = new BoardView(currentUser,opponent);
        if(BOARD.playerOneIsRed()){
            RED_PLAYER = currentUser;
            WHITE_PLAYER = opponent;
            BOARD = BOARD.rotate();
        }else{
            RED_PLAYER = opponent;
            WHITE_PLAYER = currentUser;
        }
        // build the View-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR,TITLE);
        vm.put(CURRENT_USER_ATTR,CURRENT_USER);
        vm.put(VIEW_MODE_ATTR,VIEW_MODE);
        vm.put(MODE_OPTIONS_ATTR,MODE_OPTIONS);
        vm.put(RED_PLAYER_ATTR,RED_PLAYER);
        vm.put(WHITE_PLAYER_ATTR,WHITE_PLAYER);
        vm.put(GAME_ID_ATTR,GAME_ID);
        vm.put(ACTIVE_COLOR_ATTR,ACTIVE_COLOR);
        vm.put(BOARD_ATTR,BOARD);

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));


    }
}

