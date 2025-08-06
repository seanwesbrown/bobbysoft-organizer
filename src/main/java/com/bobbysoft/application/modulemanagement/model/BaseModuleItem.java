package com.bobbysoft.application.modulemanagement.model;

public class BaseModuleItem extends ModuleItem<Boolean> {
    private String description;
    private boolean complete;

    public BaseModuleItem() {
    }

    public BaseModuleItem(String description, boolean complete) {
        this.description = description;
        this.complete = complete;
    }

    public BaseModuleItem(String description) {
        this.description = description;
        this.complete = false;
    }

    @Override
    public ModuleItemType getModuleType() {
        return ModuleItemType.BASE;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    protected boolean isItemComplete() {
        return complete;
    }

    @Override
    public void updateItemProgress(Boolean completed) {
        complete = completed;
        updateLastUpdateTimestamp();
    }

    @Override
    public void resetProgress() {
        complete = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
