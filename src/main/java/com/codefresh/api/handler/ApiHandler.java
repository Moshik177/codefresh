package com.codefresh.api.handler;

import com.codefresh.api.enums.ECrud;
import com.codefresh.api.utils.JsonConverter;
import com.codefresh.log.Log;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApiHandler implements IApiHandler {

    private static final int NUMBER_OF_MILLISECONDS = 5000;

    public ApiHandler() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.defaultParser = Parser.JSON;
        setConnectionTimeOut();
    }

    @Override
    public Response httpPost(String url, Object body, Map<String, String> additionalHeaders,
                             int expectedCode) {
        return httpRequest(url, body, additionalHeaders, expectedCode, ECrud.POST);
    }

    @Override
    public Response httpGet(String url, Map<String, String> additionalHeaders, int expectedCode) {
        return httpRequest(url, null, additionalHeaders, expectedCode, ECrud.GET);
    }

    private Response httpRequest(String url, Object body, Map<String, String> additionalHeaders,
                                 int expectedCode, ECrud crud) {
        Response response = null;
        Map<String, String> headers = setHeaders(additionalHeaders);

        Log.info(ApiHandler.class, "HTTP " + crud.name() + " to: " + url);
        setBaseURL(url);

        printHeaders(headers);
        RequestSpecification requestBuilder = RestAssured.given().headers(headers).relaxedHTTPSValidation();

        if (body != null) {
            printRequestBody(body);
            requestBuilder = requestBuilder.body(body);
        }

        switch (crud) {
            case POST:
                response = requestBuilder.when().post();
                break;
            case GET:
                response = requestBuilder.when().get();
                break;
        }

        verifyStatusCode(Objects.requireNonNull(response).getStatusCode(), expectedCode);
        printResponseBody(response.body());

        return response;
    }

    public void setConnectionTimeOut(int timeout) {
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", timeout * 1000)
                        .setParam("http.socket.timeout", timeout * 1000)
                        .setParam("http.connection-manager.timeout", timeout * 1000));
    }

    private void setConnectionTimeOut() {
        setConnectionTimeOut(NUMBER_OF_MILLISECONDS);
    }

    private static void setBaseURL(String url) {
        RestAssured.baseURI = url;
    }

    private Map<String, String> setHeaders(Map<String, String> additionalHeaders) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        if (additionalHeaders != null) {
            headers.putAll(additionalHeaders);
        }
        return headers;
    }

    private <T> void printRequestBody(T body) {

        try {
            Object gson = JsonConverter.convertObjectToJson(body, body.getClass());
            Log.info(ApiHandler.class, "Sending current body with the request: " + gson);
        } catch (Exception e) {
            Log.error(ApiHandler.class, "Error while trying to parse http body to json.");
        }
    }

    private void verifyStatusCode(int actualCode, int expectedCode) {
        if (actualCode == expectedCode) {
            Log.info(ApiHandler.class, String.format("The status code is '%s' as expected", actualCode));
        } else {
            Log.error(ApiHandler.class, String.format(
                    "The status code was not what was expected. It was '%d' but was expected to be '%d'",
                    actualCode, expectedCode));
        }
    }

    private void printHeaders(Map<String, String> headers) {
        Log.info(ApiHandler.class, "Sending request with following headers: " + headers.toString());
    }

    private void printResponseBody(ResponseBody body) {
        Log.info(ApiHandler.class, "Response Body: " + body.asString());
    }
}