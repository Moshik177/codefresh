package com.codefresh.api.models;

public class Spec {

    private Trigger[] triggers;
    private Object steps;
    private String[] stages;
    private  String[] Variables;
    private String[]  contexts;

    public Object getSteps() {
        return steps;
    }

    public Trigger[] getTriggers() {
        return triggers;
    }

    public String[] getContexts() {
        return contexts;
    }

    public String[] getVariables() {
        return Variables;
    }

    public String[] getStages() {
        return stages;
    }

    public void setTriggers(Trigger[] triggers) {
        this.triggers = triggers;
    }

    public void setContexts(String[] contexts) {
        this.contexts = contexts;
    }

    public void setStages(String[] stages) {
        this.stages = stages;
    }

    public void setSteps(Object steps) {
        this.steps = steps;
    }

    public void setVariables(String[] variables) {
        Variables = variables;
    }
}