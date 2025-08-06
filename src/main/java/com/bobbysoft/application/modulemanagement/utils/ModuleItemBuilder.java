package com.bobbysoft.application.modulemanagement.utils;

import com.bobbysoft.application.modulemanagement.model.BaseModuleItem;
import com.bobbysoft.application.modulemanagement.model.ModuleItem;
import com.bobbysoft.application.modulemanagement.model.ModuleItem.ModuleItemRepeatInterval;
import com.bobbysoft.application.modulemanagement.model.ModuleItem.ModuleItemType;
import com.bobbysoft.application.modulemanagement.model.QuantityModuleItem;

import java.time.Instant;

public class ModuleItemBuilder {

    private final ModuleItemType type;

    private String label = "";
    private ModuleItemRepeatInterval repeatType = ModuleItemRepeatInterval.NONE;
    private Instant resetDate = null;
    private Instant lastUpdateDate = null;

    private String description = "";
    private boolean complete = false;

    private Double quantityMax = 0.0;
    private Double currentQuantity = 0.0;
    private boolean displayDecimal = false;

    private ModuleItemBuilder(ModuleItemType type) {
        this.type = type;
    }

    public ModuleItemBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    public ModuleItemBuilder withRepeatType(ModuleItemRepeatInterval repeatType) {
        this.repeatType = repeatType;
        return this;
    }

    public ModuleItemBuilder withResetDate(Instant resetDate) {
        this.resetDate = resetDate;
        return this;
    }

    public ModuleItemBuilder withLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }

    public ModuleItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ModuleItemBuilder withIsComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public ModuleItemBuilder withQuantity(double currentQuantity, double maxQuantity) {
        this.currentQuantity = currentQuantity;
        this.quantityMax = maxQuantity;
        return this;
    }

    public ModuleItemBuilder withDisplayDecimal(boolean displayDecimal) {
        this.displayDecimal = displayDecimal;
        return this;
    }

    public ModuleItem<?> build() {
        ModuleItem<?> moduleItem = null;

        switch (type) {
            case ModuleItem.ModuleItemType.BASE -> {
                BaseModuleItem baseModuleItem = new BaseModuleItem();
                baseModuleItem.setDescription(description);
                baseModuleItem.setComplete(complete);

                moduleItem = baseModuleItem;
            }
            case ModuleItemType.QUANTITY -> {
                QuantityModuleItem quantityModuleItem = new QuantityModuleItem();
                quantityModuleItem.setCurrentQuantity(currentQuantity);
                quantityModuleItem.setMaxQuantity(quantityMax);
                quantityModuleItem.setDisplayDecimal(displayDecimal);

                moduleItem = quantityModuleItem;
            }
        }

        if (moduleItem != null) {
            moduleItem.setLabel(label);
            moduleItem.setRepeatType(repeatType);
            moduleItem.setResetDate(resetDate);
            moduleItem.setLastUpdateDate(lastUpdateDate);
        }

        return moduleItem;
    }

    public static ModuleItemBuilder ofType(ModuleItem.ModuleItemType type) {
        return new ModuleItemBuilder(type);
    }
}
