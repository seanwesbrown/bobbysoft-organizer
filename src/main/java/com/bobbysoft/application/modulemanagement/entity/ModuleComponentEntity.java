package com.bobbysoft.application.modulemanagement.entity;

import com.bobbysoft.application.modulemanagement.model.ModuleContent;
import com.bobbysoft.application.modulemanagement.utils.ModuleContentConverter;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "module_components")
public class ModuleComponentEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "x_position")
    private int xPosition;

    @Column(name = "y_position")
    private int yPosition;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "panel_mode")
    private boolean panelMode;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private ModuleEntity module;

    @Convert(converter = ModuleContentConverter.class)
    private ModuleContent moduleContent;

    public ModuleContent getModuleContent() {
        return moduleContent;
    }

    public void setModuleItems(ModuleContent moduleContent) {
        this.moduleContent = moduleContent;
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
