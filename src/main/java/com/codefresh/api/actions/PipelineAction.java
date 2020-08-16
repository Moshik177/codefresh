package com.codefresh.api.actions;

import com.codefresh.api.handler.ApiHandler;
import com.codefresh.api.models.PipelineRequest;
import com.codefresh.api.models.PipelineResponse;
import com.codefresh.api.models.RunPipelineRequest;
import io.restassured.response.Response;
import org.testng.Assert;

public class PipelineAction extends BaseAction {

    private final String suffix = "/pipelines";
    private final String runSuffix = "/run";

    public PipelineAction(String baseUrl){
        super(new ApiHandler(),baseUrl);
    }

    public PipelineResponse createPipeline( PipelineRequest pipelineRequest ){
      try {
          setAuthorizationHeader();
          Response response = handler.httpPost(baseUrl + suffix, pipelineRequest, headers, 200);
          verifyStatusCode(response);
          return response.as(PipelineResponse.class);
      } catch (Exception e){
          Assert.fail("The request didn't succeeded ,error:" + e.getMessage());
          return null;
      }
    }

    public String runPipeline( RunPipelineRequest pipelineRequest,String pipelineName ){
        try {
            setAuthorizationHeader();
            String requestUrl = baseUrl + suffix + runSuffix+ "/" +pipelineName;
            Response response = handler.httpPost(requestUrl, pipelineRequest, headers, 200);
            verifyStatusCode(response);
            return response.as(String.class);
        } catch (Exception e){
            Assert.fail("The request didn't succeeded ,error:" + e.getMessage());
            return null;
        }
    }
}