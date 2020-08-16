package com.codefresh.api.enums;

public enum ECrud {

    POST("Post"),
    PUT("Put"),
    DELETE("Delete"),
    GET("Get"),
    UPLOAD_POST("Upload_Post"),
    PATCH("Patch");

    private final String type;

    ECrud(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }
}