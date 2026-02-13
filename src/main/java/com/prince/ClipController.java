package com.prince;

import org.springframework.web.bind.annotation.*;

@RestController
public class ClipController {

    @GetMapping("/clip")
    public String handleClip(
            @RequestParam String user,
            @RequestParam(required = false) String message) {

        if (message == null || message.trim().isEmpty()) {
            message = "No description provided";
        }

        return "Clip received from " + user + " : " + message;
    }
}
