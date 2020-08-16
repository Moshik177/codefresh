package com.codefresh.selenium.createInstance;

public enum BrowserType {
    FIREFOX_DRIVER("firefox"),
    INTERNET_EXPLORER_DRIVER("IE")
    ,CHROME_DRIVER("chrome");

    private final String webDriverType;

    BrowserType(String type) {
        webDriverType = type;
    }

    public String getBrowserType() {
        return webDriverType;
    }
}

