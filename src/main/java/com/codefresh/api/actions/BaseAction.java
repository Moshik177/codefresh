package com.codefresh.api.actions;

import com.codefresh.api.handler.IApiHandler;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class BaseAction {

    protected final IApiHandler handler;
    protected final String baseUrl;
    protected final Map<String,String> headers;
    private final String ApiKey = "5f3040fa50270d57f9686b12.5342307beb65d833ed0dd0213d14144d";


    public BaseAction(IApiHandler handler, String baseUrl){
        this.handler = handler;
        this.baseUrl = baseUrl;
        this.headers = new HashMap<>();
    }

    public void setAuthorizationHeader(){
        headers.put("Authorization",ApiKey);
    }

    public void verifyStatusCode(Response response){
       if(response.statusCode() != 200 && response.statusCode() != 201  && response.statusCode() != 304  )
           Assert.fail("Http request return status code: " + response.statusCode() +
                   " and the error is: " +response.getBody().prettyPrint());
    }
}
