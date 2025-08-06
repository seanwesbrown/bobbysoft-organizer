package com.bobbysoft.application.modulemanagement.repository;

import com.bobbysoft.application.modulemanagement.entity.ModuleComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ModuleComponentRepository extends JpaRepository<ModuleComponentEntity, UUID>, JpaSpecificationExecutor<ModuleComponentEntity> {
}
