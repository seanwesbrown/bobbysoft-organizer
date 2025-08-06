package com.bobbysoft.application.modulemanagement.entity;

import com.bobbysoft.application.usermanagement.entity.UserEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "modules")
public class ModuleEntity {
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

    @Column(name = "primary_color_hex")
    private String primaryColorHex;

    @Column(name = "secondary_color_hex")
    private String secondaryColorHex;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "module", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<ModuleComponentEntity> moduleComponents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity owner) {
        this.user = owner;
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
