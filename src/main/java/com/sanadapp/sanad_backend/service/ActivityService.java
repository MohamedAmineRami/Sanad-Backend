package com.sanadapp.sanad_backend.service;

import com.sanadapp.sanad_backend.entity.Activity;
import com.sanadapp.sanad_backend.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public List<Activity> getPublicActivities() {
        return activityRepository.findByIsPublicTrue();
    }

    public List<Activity> getRecentPublicActivities(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        return activityRepository.findByIsPublicTruePageable(pageable);
    }
}