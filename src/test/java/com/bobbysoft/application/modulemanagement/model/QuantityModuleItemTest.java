package com.bobbysoft.application.modulemanagement.model;

import com.bobbysoft.application.modulemanagement.utils.ModuleItemBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuantityModuleItemTest {

    @Test
    @DisplayName("Formatted quantities show decimal when display decimal is set to true")
    public void testQuantityFormattingWithDecimal() {
        QuantityModuleItem moduleItem = (QuantityModuleItem) ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.QUANTITY)
                .withQuantity(32.15, 77.77)
                .withDisplayDecimal(true)
                .build();

        assert moduleItem.getFormattedCurrentQuantity().equals("32.15");
        assert moduleItem.getFormattedMaxQuantity().equals("77.77");
    }

    @Test
    @DisplayName("Formatted quantities hide decimal when display decimal is set to false")
    public void testQuantityFormattingWithoutDecimal() {
        QuantityModuleItem moduleItem = (QuantityModuleItem) ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.QUANTITY)
                .withQuantity(32.00, 77.00)
                .withDisplayDecimal(false)
                .build();

        assert moduleItem.getFormattedCurrentQuantity().equals("32");
        assert moduleItem.getFormattedMaxQuantity().equals("77");
    }

}
