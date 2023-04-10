package com.muhardin.endy.belajar.authentication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedApiController {

    @GetMapping("/api/protected/userinfo")
    public Map<String, Object> userinfo(Authentication currentUser){
        Map<String, Object> info = new HashMap<>();
        info.put("username", currentUser.getPrincipal());
        info.put("permissions", currentUser.getAuthorities());
        return info;
    }
}
