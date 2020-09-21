package com.gilad.ratelimiterservice.service;

public class UserData {
    private int RemainingRequests ;
    private long intervalStartTime;

    public UserData(int remainingRequests) {
        this.RemainingRequests = remainingRequests;
        this.intervalStartTime = System.currentTimeMillis();
    }

    public int getRemainingRequests() {
        return RemainingRequests;
    }

    public void setRemainingRequests(int remainingRequests) {
        RemainingRequests = remainingRequests;
    }

    public long getIntervalStartTime() {
        return intervalStartTime;
    }

    public void setIntervalStartTime(long intervalStartTime) {
        this.intervalStartTime = intervalStartTime;
    }

    public synchronized boolean decreaseRemainingRequests(){
        if(RemainingRequests > 0)
            RemainingRequests -= 1;
        else
            return false;
        return true;
    }


}