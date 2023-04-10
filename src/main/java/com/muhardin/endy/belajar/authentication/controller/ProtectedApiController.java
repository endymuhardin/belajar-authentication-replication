package com.muhardin.endy.belajar.authentication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedApiController {

    @GetMapping("/api/protected/userinfo")
    public Map<String, String> userinfo(){
        Map<String, String> info = new HashMap<>();
        info.put("username", "testuser");
        return info;
    }
}
