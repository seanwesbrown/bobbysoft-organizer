package com.bobbysoft.application.modulemanagement.domain;

public class BaseModuleItem extends ModuleItem<Boolean> {
    private String description;
    private boolean complete;

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

    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public void updateProgress(Boolean completed) {
        complete = completed;
        updateCompletionTimestamp(completed);
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
