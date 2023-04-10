package com.muhardin.endy.belajar.authentication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ProtectedApiController {

    @GetMapping("/api/protected/userinfo")
    public Map<String, Object> userinfo(Authentication currentUser, HttpServletRequest request){
        Map<String, Object> info = new HashMap<>();
        info.put("username", currentUser.getPrincipal());
        info.put("permissions", currentUser.getAuthorities());
        info.put("server-ip", request.getLocalAddr());
        info.put("server-port", request.getLocalPort());
        info.put("client-ip", request.getRemoteAddr());
        info.put("client-port", request.getRemotePort());
        return info;
    }
}
