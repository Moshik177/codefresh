package com.codefresh.api.models;

import java.util.ArrayList;

public class Trigger  {

    private String name;
    private String type;
    private String repo;
    private ArrayList<String> events;
    private boolean pullRequestAllowForkEvents;
    private String commentRegex;
    private String branchRegex;
    private String branchRegexInput;
    private String provider;
    private boolean disabled;
    private Options options;
    private String context;
    private ArrayList<ArrayList<String>> contexts;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public boolean isPullRequestAllowForkEvents() {
        return pullRequestAllowForkEvents;
    }

    public String getBranchRegex() {
        return branchRegex;
    }

    public String getCommentRegex() {
        return commentRegex;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public String getBranchRegexInput() {
        return branchRegexInput;
    }

    public String getRepo() {
        return repo;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Options getOptions() {
        return options;
    }

    public String getProvider() {
        return provider;
    }

    public String getContext() {
        return context;
    }

    public String getType() {
        return type;
    }

    public void setBranchRegex(String branchRegex) {
        this.branchRegex = branchRegex;
    }

    public void setContexts(ArrayList<ArrayList<String>> contexts) {
        this.contexts = contexts;
    }

    public void setBranchRegexInput(String branchRegexInput) {
        this.branchRegexInput = branchRegexInput;
    }

    public ArrayList<ArrayList<String>> getContexts() {
        return contexts;
    }

    public void setCommentRegex(String commentRegex) {
        this.commentRegex = commentRegex;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setPullRequestAllowForkEvents(boolean pullRequestAllowForkEvents) {
        this.pullRequestAllowForkEvents = pullRequestAllowForkEvents;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }
}