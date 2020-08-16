package com.codefresh.api.models;

public class RunPipelineRequest {

    private boolean isYamlService;
    private String branch;
    private Options options;
    private Variable variables;
    private String serviceId;
    private String serviceName;
    private String trigger;
    private String type;

    public boolean getIsYamlService() {
        return isYamlService;
    }

    public Options getOptions() {
        return options;
    }

    public String getBranch() {
        return branch;
    }

    public Variable getVariables() {
        return variables;
    }

    public String getTrigger() {
        return trigger;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setIsYamlService(boolean yamlService) {
        isYamlService = yamlService;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setYamlService(boolean yamlService) {
        isYamlService = yamlService;
    }

    public void setVariables(Variable variables) {
        this.variables = variables;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
       return type;
    }
}
