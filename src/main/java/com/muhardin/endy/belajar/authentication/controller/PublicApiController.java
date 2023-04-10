package com.muhardin.endy.belajar.authentication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PublicApiController {

    @Autowired private GitProperties gitProperties;

    @GetMapping("/api/public/appinfo")
    public Map<String, Object> appinfo(HttpServletRequest request){
        Map<String, Object> info = new HashMap<>();
        info.put("server-ip", request.getLocalAddr());
        info.put("server-port", request.getLocalPort());
        info.put("client-ip", request.getRemoteAddr());
        info.put("client-port", request.getRemotePort());
        info.put("app-version", gitProperties.getShortCommitId());
        return info;
    }
}
