package com.sanadapp.sanad_backend.repository;

import com.sanadapp.sanad_backend.entity.Activity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, String> {
    List<Activity> findByIsPublicTrue();

    @Query("SELECT a FROM Activity a WHERE a.isPublic = true ORDER BY a.createdAt DESC")
    List<Activity> findByIsPublicTruePageable(Pageable pageable);
}