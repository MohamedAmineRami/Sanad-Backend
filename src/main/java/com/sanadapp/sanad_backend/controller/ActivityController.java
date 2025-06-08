package com.sanadapp.sanad_backend.controller;

import com.sanadapp.sanad_backend.entity.Activity;
import com.sanadapp.sanad_backend.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/public")
    public List<Activity> getPublicFeed() {
        return activityService.getPublicActivities();
    }

    @GetMapping("/recent")
    public List<Activity> getRecentActivities(@RequestParam(value = "limit", defaultValue = "10") int limit) {
        return activityService.getRecentPublicActivities(limit);
    }
}