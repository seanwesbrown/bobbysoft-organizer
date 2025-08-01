package com.bobbysoft.application.modulemanagement.domain;

import java.text.DecimalFormat;

public class QuantityModuleItem extends ModuleItem<Double> {
    private static final DecimalFormat displayDecimalFormat = new DecimalFormat("0.##");
    private static final DecimalFormat hideDecimalFormat = new DecimalFormat("#");

    private Double quantityMax = 0.0;
    private Double currentQuantity = 0.0;
    private boolean displayDecimal = false;

    public QuantityModuleItem(Double quantityMax, Double currentQuantity, boolean displayDecimal) {
        this.quantityMax = quantityMax;
        this.currentQuantity = currentQuantity;
        this.displayDecimal = displayDecimal;
    }

    public QuantityModuleItem(Double quantityMax) {
        this.quantityMax = quantityMax;
    }

    @Override
    public boolean isComplete() {
        return currentQuantity >= quantityMax;
    }

    @Override
    public void updateProgress(Double progress) {
        currentQuantity = progress;

        if (currentQuantity >= quantityMax) {
            currentQuantity = quantityMax;
            updateCompletionTimestamp(true);
        } else {
            updateCompletionTimestamp(false);
        }
    }

    @Override
    public void resetProgress() {
        currentQuantity = 0.0;
    }

    @Override
    public ModuleItemType getModuleType() {
        return ModuleItemType.QUANTITY;
    }

    public Double getQuantityMax() {
        return quantityMax;
    }

    public void setQuantityMax(Double quantityMax) {
        this.quantityMax = quantityMax;
    }

    public boolean isDisplayDecimal() {
        return displayDecimal;
    }

    public void setDisplayDecimal(boolean displayDecimal) {
        this.displayDecimal = displayDecimal;
    }

    public Double getCurrentQuantity() {
        return currentQuantity;
    }

    public String getFormattedCurrentQuantity() {
        return getQuantityFormatter().format(currentQuantity);
    }

    public String getFormattedQuantityMax() {
        return getQuantityFormatter().format(quantityMax);
    }

    private DecimalFormat getQuantityFormatter() {
        return displayDecimal ? displayDecimalFormat : hideDecimalFormat;
    }
}
