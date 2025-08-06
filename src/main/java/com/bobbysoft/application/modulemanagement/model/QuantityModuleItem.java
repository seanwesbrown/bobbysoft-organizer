package com.bobbysoft.application.modulemanagement.model;

import java.text.DecimalFormat;

public class QuantityModuleItem extends ModuleItem<Double> {
    private static final DecimalFormat displayDecimalFormat = new DecimalFormat("0.##");
    private static final DecimalFormat hideDecimalFormat = new DecimalFormat("#");

    private Double maxQuantity = 0.0;
    private Double currentQuantity = 0.0;
    private boolean displayDecimal = false;

    public QuantityModuleItem() {
    }

    public QuantityModuleItem(Double maxQuantity, Double currentQuantity, boolean displayDecimal) {
        this.maxQuantity = maxQuantity;
        this.currentQuantity = currentQuantity;
        this.displayDecimal = displayDecimal;
    }

    public QuantityModuleItem(Double maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Override
    protected boolean isItemComplete() {
        return currentQuantity >= maxQuantity;
    }

    @Override
    public void updateItemProgress(Double progress) {
        currentQuantity = progress;

        if (currentQuantity >= maxQuantity) {
            currentQuantity = maxQuantity;

        }

        updateLastUpdateTimestamp();
    }

    @Override
    public void resetProgress() {
        currentQuantity = 0.0;
    }

    @Override
    public ModuleItemType getModuleType() {
        return ModuleItemType.QUANTITY;
    }

    public void setCurrentQuantity(Double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Double getCurrentQuantity() {
        return currentQuantity;
    }

    public Double getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Double maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public boolean isDisplayDecimal() {
        return displayDecimal;
    }

    public void setDisplayDecimal(boolean displayDecimal) {
        this.displayDecimal = displayDecimal;
    }

    public String getFormattedCurrentQuantity() {
        return getQuantityFormatter().format(currentQuantity);
    }

    public String getFormattedMaxQuantity() {
        return getQuantityFormatter().format(maxQuantity);
    }

    private DecimalFormat getQuantityFormatter() {
        return displayDecimal ? displayDecimalFormat : hideDecimalFormat;
    }
}
