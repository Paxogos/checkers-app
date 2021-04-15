package com.webcheckers.ui;

import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class GetSignInRoute implements Route {
    private final TemplateEngine templateEngine;

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private static final Message SIGN_IN_MSG = Message.info("Select a name to get started.");


    /**
     * Route controller for retrieving the sign-in page
     *
     * @param templateEngine    TemplateEngine used for rendering .html files
     */
    public GetSignInRoute(final TemplateEngine templateEngine) {

        // Validation
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetSignInRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetSignInRoute is invoked.");
        Session httpSession = request.session();

        // If this is a new browser/session
        if (httpSession.attribute(GetHomeRoute.PLAYER_ATTR) == null)
            return templateEngine.render(getSignInPage(SIGN_IN_MSG));

        else {
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }
    }

    /**
     * Helper method for getting the ModelAndView of the Sign-In page
     *
     * @param message   message to display
     * @return          contents of the Sign-In page to be rendered
     */
    public static ModelAndView getSignInPage(Message message) {
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign In");

        // display a user message in the Home page
        vm.put("message", message);

        return new ModelAndView(vm, "signin.ftl");
    }
}
