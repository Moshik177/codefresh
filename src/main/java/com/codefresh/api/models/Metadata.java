package com.codefresh.api.models;

public class Metadata {

    private String accountId;
    private String name;
    private int revision;
    private String originalYamlString;

    public String getName() {
        return name;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getRevision() {
        return revision;
    }

    public String getOriginalYamlString() {
        return originalYamlString;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setOriginalYamlString(String originalYamlString) {
        this.originalYamlString = originalYamlString;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }
}