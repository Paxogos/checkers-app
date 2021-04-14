package com.webcheckers.ui;

import java.util.*;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
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
    static final String MESSAGE_ATTR = "message";
    static final String DIRECT_MESSAGE_ATTR = "directMessages";



    static final String MOST_RECENT_GAME_ATTR = "mostRecentGame";
    static final String GAME_OVER_MESSAGE_ATTR = "gameOverMessage";

    static String TITLE = "Game";
    static String VIEW_NAME = "game.ftl";
    static String VIEW_MODE = "PLAY";
    static String MODE_OPTIONS = "{}";


    private PlayerLobby playerLobby;
    private GameCenter gameCenter;

    // Values used in the view-model map for rendering the game view.
    private final TemplateEngine templateEngine;
    private final Gson gson;

    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine The {@link TemplateEngine} used for rendering page HTML.
     * @param gson
     */
    GetGameRoute(PlayerLobby playerLobby, GameCenter gameCenter, final TemplateEngine templateEngine, Gson gson) {
        this.gson = gson;
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

        Game currentGame = null;
        BoardView boardview;

        final Session httpSession = request.session();
        if(httpSession.attribute(PLAYER_ATTR) == null){
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }

        Player currentUser = httpSession.attribute(PLAYER_ATTR);

        // If the request did not provide an opponent, check if the currentUser has a game in attributes
        String opponentString = request.queryParams("opp");
        if(opponentString==null){
            currentGame = httpSession.attribute(MOST_RECENT_GAME_ATTR);
        }

        Player opponent = playerLobby.getPlayer(opponentString);
        String gameIDString = request.queryParams("gameID");



        // build the View-Model
        final Map<String, Object> vm = new HashMap<>();

        //modeOptions decides what game state the game is in.
        final Map<String, Object> modeOptions = new HashMap<>(2);


        // game exists, retrieve game and render FTL accordingly
        if(gameIDString != null || gameCenter.gameExists(currentUser,opponent) || currentGame!=null){

            if(gameIDString != null){
                currentGame = gameCenter.getGame(Integer.parseInt(gameIDString));
            }else if(gameCenter.gameExists(currentUser,opponent)){
                currentGame = gameCenter.getGame(currentUser, opponent);
            }
            vm.put(DIRECT_MESSAGE_ATTR,currentGame.getDirectMessages());
            vm.put(RED_PLAYER_ATTR,currentGame.getRedPlayer());
            vm.put(WHITE_PLAYER_ATTR,currentGame.getWhitePlayer());
            vm.put(ACTIVE_COLOR_ATTR, currentGame.getActiveColor());

            /**
             * If the game is over and the game was reigned, then the game will end and display a message of the
             * player who resigned
             */
            if (currentGame.isGameOver() && currentGame.getResignee() != null) {
                Player resignee = currentGame.getResignee();
                Player winningPlayer = gameCenter.getCurrentOpponent(resignee);
                modeOptions.put(GAME_OVER_MESSAGE_ATTR, resignee.getName() + " has resigned. " + winningPlayer.getName() + " wins by default");
                httpSession.removeAttribute(GetGameRoute.GAME_ATTR);
            }

            /**
             * If the game is over without someone resigning, then the game is over and just displays the winner
             */
            else if (currentGame.isGameOver()) {
                modeOptions.put(GAME_OVER_MESSAGE_ATTR, currentGame.getWinner() + "has captured all the pieces.");
                httpSession.removeAttribute(GetGameRoute.GAME_ATTR);
            }


            //generates the board view for the current user
            if(currentUser == currentGame.getRedPlayer()){
                boardview = currentGame.getBoard().getBoardViewForRed();
            }else{
                boardview = currentGame.getBoard().getBoardViewForWhite();
            }
            vm.put(BOARD_ATTR, boardview);


        }else if(gameCenter.isPlayerInGame(opponent)){ // if the opponent is already in a game we have to send request
            playerLobby.newGameNotification(currentUser, opponent);
            response.redirect(WebServer.HOME_URL);
            return null;
        }else{ // create a new game, opponent has no other games
            opponent = playerLobby.getPlayer(opponentString);
            playerLobby.gameStartedNotification(currentUser,opponent);

            currentGame = gameCenter.getGame(currentUser, opponent);

            if(currentGame.isGameOver()) {
                gameCenter.removeGame(currentUser, opponent);
                response.redirect("/");
                halt();
                return null;
            }


            vm.put(RED_PLAYER_ATTR, currentUser);
            vm.put(WHITE_PLAYER_ATTR, opponent);
            vm.put(ACTIVE_COLOR_ATTR, Piece.Color.RED);
            vm.put(BOARD_ATTR, currentGame.getBoard().getBoardViewForRed());
        }

        httpSession.attribute(MOST_RECENT_GAME_ATTR, currentGame);

        //population the view models to generate the game page
        vm.put(TITLE_ATTR, TITLE);
        vm.put(CURRENT_USER_ATTR, currentUser);
        vm.put(VIEW_MODE_ATTR, VIEW_MODE);
        vm.put(GAME_ID_ATTR, currentGame.getGameID());




        /**
         * Checks to see if the game is over. If true, then the gae over state is triggered,
         * otherwise the game continues and the game state doesn't change
         */
        if(currentGame.isGameOver())
            vm.put(MODE_OPTIONS_ATTR, gson.toJson(modeOptions));
        else
            vm.put(MODE_OPTIONS_ATTR, MODE_OPTIONS);

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));


    }

}
