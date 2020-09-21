package com.gilad.ratelimiterservice.dao;

public class Response {

    private boolean allow;

    public Response(boolean allow) {
        this.allow = allow;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }
}
