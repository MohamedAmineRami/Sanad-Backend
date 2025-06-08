package com.sanadapp.sanad_backend.repository;

import com.sanadapp.sanad_backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
}
