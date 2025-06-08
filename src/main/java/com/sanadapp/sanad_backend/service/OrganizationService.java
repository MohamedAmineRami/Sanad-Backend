package com.sanadapp.sanad_backend.service;

import com.sanadapp.sanad_backend.entity.Organization;
import com.sanadapp.sanad_backend.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }
}
