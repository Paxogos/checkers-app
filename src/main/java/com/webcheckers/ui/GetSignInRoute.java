package com.webcheckers.ui;

import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetSignInRoute implements Route {

    private final TemplateEngine templateEngine;

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private static final Message SIGN_IN_MSG = Message.info("Select a name to get started.");

    public GetSignInRoute(final TemplateEngine templateEngine) {

        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetSignInRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetHomeRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");

        // display a user message in the Home page
        vm.put("message", SIGN_IN_MSG);

        return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
    }
}
