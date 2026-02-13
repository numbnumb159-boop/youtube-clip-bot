package com.prince;

import org.springframework.web.bind.annotation.*;
import java.time.Instant;

@RestController
public class ClipController {

    private static Instant streamStartTime;
    private static String currentVideoId;

    @GetMapping("/start")
    public String startStream(@RequestParam String videoId) {
        streamStartTime = Instant.now();
        currentVideoId = videoId;
        return "Stream started with video ID: " + videoId;
    }

    @GetMapping("/clip")
    public String handleClip(
            @RequestParam String user,
            @RequestParam(required = false) String message) {

        if (streamStartTime == null || currentVideoId == null) {
            return "Stream not started yet!";
        }

        if (message == null || message.trim().isEmpty()) {
            message = "No description provided";
        }

        long secondsLive = Instant.now().getEpochSecond() - streamStartTime.getEpochSecond();
        long adjustedSeconds = Math.max(secondsLive - 40, 0);

        String vodLink = "https://youtube.com/watch?v=" + currentVideoId + "&t=" + adjustedSeconds + "s";

        return "🎬 " + user + " clipped: " + message + " | " + vodLink;
    }
}
