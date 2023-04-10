package com.muhardin.endy.belajar.authentication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PublicApiController {

    @GetMapping("/api/public/appinfo")
    public Map<String, String> appinfo(HttpServletRequest request){
        Map<String, String> info = new HashMap<>();
        info.put("ip-server", request.getLocalAddr());
        info.put("ip-client", request.getRemoteAddr());
        return info;
    }
}
