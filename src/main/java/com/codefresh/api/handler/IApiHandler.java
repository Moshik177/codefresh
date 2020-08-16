package com.codefresh.api.handler;

import java.util.Map;
import io.restassured.response.Response;

/**
 * Defines service to send http requests
 *
 * @author moshik.kalash
 */
public interface IApiHandler {


    /**
     * get request
     *
     * @param url          the url of request
     * @param headers      the headers of the request
     * @param expectedCode the expected code (200)
     * @return response of the request
     */
    Response httpGet(String url, Map<String, String> headers, int expectedCode);

    /**
     * send post request full parameters
     *
     * @param url               the url of request
     * @param additionalHeaders the headers of the request
     * @param body              the body of the request
     * @param expectedCode      the expected code (201)
     * @return response of the request
     */
    Response httpPost(String url, Object body, Map<String, String> additionalHeaders,
                      int expectedCode);
}
