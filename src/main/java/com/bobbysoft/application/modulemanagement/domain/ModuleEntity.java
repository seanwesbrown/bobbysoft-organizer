package com.bobbysoft.application.modulemanagement.domain;

import com.bobbysoft.application.usermanagement.domain.UserEntity;

import java.util.List;

public class ModuleEntity {
    private String name;
    private UserEntity owner;

    private int xPosition;
    private int yPosition;

    private int width;
    private int height;

    private String primaryColorHex;
    private String secondaryColorHex;

    private List<ModuleComponentEntity> moduleComponents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPrimaryColorHex() {
        return primaryColorHex;
    }

    public void setPrimaryColorHex(String primaryColorHex) {
        this.primaryColorHex = primaryColorHex;
    }

    public String getSecondaryColorHex() {
        return secondaryColorHex;
    }

    public void setSecondaryColorHex(String secondaryColorHex) {
        this.secondaryColorHex = secondaryColorHex;
    }

    public List<ModuleComponentEntity> getModuleComponents() {
        return moduleComponents;
    }

    public void setModuleComponents(List<ModuleComponentEntity> moduleComponents) {
        this.moduleComponents = moduleComponents;
    }
}
