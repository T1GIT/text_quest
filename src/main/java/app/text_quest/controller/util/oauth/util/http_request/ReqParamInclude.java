package app.text_quest.controller.util.oauth.util.http_request;

import app.text_quest.controller.util.oauth.enums.ReqParam;


public interface ReqParamInclude<T> {

    T addParam(String param, String value);

    T addParam(ReqParam oauthParam, String value);
}
