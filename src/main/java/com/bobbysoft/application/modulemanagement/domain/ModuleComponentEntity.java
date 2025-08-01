package com.bobbysoft.application.modulemanagement.domain;

import java.util.List;

public class ModuleComponentEntity {
    private String name;
    private ModuleEntity module;

    private int xPosition;
    private int yPosition;

    private int width;
    private int height;

    private boolean panelMode;

    private List<ModuleItem<?>> moduleItems;

    public List<ModuleItem<?>> getModuleItems() {
        return moduleItems;
    }

    public void setModuleItems(List<ModuleItem<?>> moduleItems) {
        this.moduleItems = moduleItems;
    }

    public boolean isPanelMode() {
        return panelMode;
    }

    public void setPanelMode(boolean panelMode) {
        this.panelMode = panelMode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public ModuleEntity getModule() {
        return module;
    }

    public void setModule(ModuleEntity module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
