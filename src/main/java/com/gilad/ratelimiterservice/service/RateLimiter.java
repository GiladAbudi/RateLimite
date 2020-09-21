package com.gilad.ratelimiterservice.service;


import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final int requestRate = 5;
    private final long interval = 10000; // in ms

    private ConcurrentHashMap<String,UserData> userDataMap;

    public RateLimiter() {
        this.userDataMap = new ConcurrentHashMap();
    }

    public boolean addRequest (String userIP){
        UserData userData;
        synchronized(this) {
            if (!userDataMap.containsKey(userIP)) {
                userData = new UserData(requestRate);
                userDataMap.put(userIP, userData);
            }
        }
        userData = userDataMap.get(userIP);
        synchronized(userData) {
            updateIntervalStartTime(userData);
        }
        return userData.decreaseRemainingRequests();
    }

    private void updateIntervalStartTime(UserData userData){
        if(System.currentTimeMillis() - userData.getIntervalStartTime() > interval) {
            userData.setIntervalStartTime(System.currentTimeMillis());
            userData.setRemainingRequests(requestRate);
        }
    }

}
