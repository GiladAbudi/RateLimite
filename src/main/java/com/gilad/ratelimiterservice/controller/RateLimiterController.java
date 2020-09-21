package com.gilad.ratelimiterservice.controller;


import com.gilad.ratelimiterservice.dao.ReportEntity;
import com.gilad.ratelimiterservice.dao.Response;
import com.gilad.ratelimiterservice.service.RateLimiter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class RateLimiterController {
    private RateLimiter rateLimiter = new RateLimiter();

    @PostMapping("/report")
    public Response response(@RequestBody ReportEntity url, HttpServletRequest request) throws UnknownHostException {
        String userIP = getIP(request.getRemoteAddr());
        //System.out.println("POST from ip: " + userIP + ", url: "+url.getUrl());
        return new Response(rateLimiter.addRequest(userIP+" "+url.getUrl()));
    }

    public String getIP(String ip) throws UnknownHostException {
        if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
            return InetAddress.getLocalHost().getHostAddress();
        }
        return ip;
    }


}