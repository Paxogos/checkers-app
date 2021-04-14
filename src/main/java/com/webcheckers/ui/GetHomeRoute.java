package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  //values used in the view-model map for rendering home view
  static final String TITLE_ATTR = "title";
  static final String WELCOME_MSG_ATTR = "message";
  static final String AVAILABLE_PLAYER_LIST_ATTR = "availablePlayerList";
  static final String PLAYER_ATTR = "currentUser";
  static final String CURRENT_GAME_LIST_ATTR = "currentGameList";
  static final String LOBBY_SIZE_ATTR = "lobbySize";
  static final String TITLE = "Welcome to Webcheckers!";
  static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  static final String VIEW_NAME = "home.ftl";
  static final String SIGNED_OUT_ATTR = "playerSignOut";

  //Attributes
  private final PlayerLobby playerLobby;
  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;


  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    this.gameCenter = gameCenter;
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    Session httpSession = request.session();

    // Retrieve the Player object from the user's session
    Player currentUser = httpSession.attribute(PLAYER_ATTR);

    if(request.queryParams("delGame") != null){
      Game currentGame = request.session().attribute(GetGameRoute.MOST_RECENT_GAME_ATTR);
      httpSession.attribute(GetGameRoute.MOST_RECENT_GAME_ATTR,null);
      if(currentGame == null){
        response.redirect("/");
      }else if(currentUser != currentGame.getResignee()){
        gameCenter.removeGame(currentGame.getResignee(),currentUser,currentGame.getGameID());
      }
    }


    Message message;
    if (httpSession.attribute(SIGNED_OUT_ATTR) != null) {
      message = Message.info("You've successfully signed out");
    }
    else
      message = WELCOME_MSG;

    // Render the home page
    return templateEngine.render(getHomePage(currentUser, this.playerLobby, gameCenter,message));

  }

  /**
   * Helper method for generating the ModelAndView for the Home page
   *
   * @param currentUser   The Player object of the current user, if there is one
   * @param playerLobby   The lobby containing all Players currently playing
   * @return              The home page contents to be rendered
   */
  public static ModelAndView getHomePage(Player currentUser, PlayerLobby playerLobby, GameCenter gameCenter, Message message) {
    Map<String, Object> vm = new HashMap<>();
    vm.put(TITLE_ATTR, TITLE);

    // display a user message in the Home page
    vm.put(WELCOME_MSG_ATTR, message);

    vm.put(PLAYER_ATTR, currentUser);

    vm.put(LOBBY_SIZE_ATTR, playerLobby.getNumberPlayers());


    if (currentUser != null) {
      if(playerLobby.hasNotification(currentUser)){
        vm.put("notification", playerLobby.getPlayerNotification(currentUser));
      }
      vm.put(AVAILABLE_PLAYER_LIST_ATTR, playerLobby.getAvailablePlayers(currentUser.getName()));
      vm.put(CURRENT_GAME_LIST_ATTR, gameCenter.getCurrentGameList(currentUser));
    }

    return new ModelAndView(vm, VIEW_NAME);
  }
}