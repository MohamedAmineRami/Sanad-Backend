package com.sanadapp.sanad_backend.controller;

import com.sanadapp.sanad_backend.entity.Organization;
import com.sanadapp.sanad_backend.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public List<Organization> getAll() {
        return organizationService.getAll();
    }
}
