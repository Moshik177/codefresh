package com.codefresh.api.actions;

import com.codefresh.api.handler.ApiHandler;
import com.codefresh.api.models.BuildResponse;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.stream.IntStream;

public class BuildAction extends BaseAction {

    private final String suffix = "/builds";

    public BuildAction(String baseUrl) {
        super(new ApiHandler(), baseUrl);
    }

    private BuildResponse getBuild(String runId) {
        try {
            setAuthorizationHeader();
            Response response = handler.httpGet(baseUrl + suffix + "/" + runId, headers, 200);
            return response.as(BuildResponse.class);
        } catch (Exception e) {
            Assert.fail("The request didn't succeded ,error:" + e.getMessage());
            return null;
        }
    }

    public boolean verifyBuildStatusSuccess(String runId, int retriesNumber, int interval) {

        return IntStream.range(0, retriesNumber).peek(n -> sleep(interval)).anyMatch(n ->
        {
            BuildResponse build = getBuild(runId);
            return build.getStatus().equals("success");
        });
    }

    private void sleep(int interval) {
        try {
            Thread.sleep(interval * 1000);
        } catch (InterruptedException ignored) {
        }
    }
}