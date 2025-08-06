package com.bobbysoft.application.usermanagement.repository;

import com.bobbysoft.application.usermanagement.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, UUID>, JpaSpecificationExecutor<AuthorityEntity> {
}
