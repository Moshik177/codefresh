package com.codefresh.api.models;

public class Options {

    private boolean noCache;
    private boolean noCfCache;
    private boolean resetVolume;
    private boolean enableNotifications;

    public boolean getNoCache() {
        return noCache;
    }

    public boolean getNoCfCache() {
        return noCfCache;
    }

    public boolean getEnableNotifications() {
        return enableNotifications;
    }

    public boolean getResetVolume() {
        return resetVolume;
    }
}