package app.text_quest.controller.util.oauth.util;

import javax.servlet.http.HttpServletRequest;


public interface OauthController {

    // TODO: 25.02.2021 Random links for users
    String receiveCode(HttpServletRequest request);

    void receiveToken(HttpServletRequest request);

    void receiveId(HttpServletRequest request);

    // TODO: 26.02.2021 State param in oauth requests CSRF
}
