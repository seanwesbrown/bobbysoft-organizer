package com.bobbysoft.application.modulemanagement.utils;

import com.bobbysoft.application.modulemanagement.model.BaseModuleItem;
import com.bobbysoft.application.modulemanagement.model.ModuleContent;
import com.bobbysoft.application.modulemanagement.model.ModuleItem;
import com.bobbysoft.application.modulemanagement.model.QuantityModuleItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@DisplayName("Module Content Converter Tests")
public class ModuleContentConverterTest {

    @Test
    @DisplayName("Converts module content to and from json")
    public void testModuleConversion() {
        ModuleContentConverter converter = new ModuleContentConverter();
        ModuleContent testModule = testData();

        String convertedJson = converter.convertToDatabaseColumn(testModule);
        assert convertedJson.contains("QuantityModuleItem");
        assert convertedJson.contains("BaseModuleItem");

        ModuleContent moduleContent = converter.convertToEntityAttribute(convertedJson);
        List<ModuleItem<?>> moduleItems = moduleContent.getModuleItems();

        assert moduleItems.size() == 2;
        assert moduleItems.get(0).getClass() == BaseModuleItem.class;
        assert moduleItems.get(0).getModuleType() == ModuleItem.ModuleItemType.BASE;

        assert moduleItems.get(1).getClass() == QuantityModuleItem.class;
        assert moduleItems.get(1).getModuleType() == ModuleItem.ModuleItemType.QUANTITY;
    }

    private ModuleContent testData() {
        ModuleContent content = new ModuleContent();
        LinkedList<ModuleItem<?>> moduleItems = new LinkedList<>();

        ModuleItem<?> moduleItemOne = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                .withDescription("Description of test item one")
                .withIsComplete(true)
                .withLabel("Test item one")
                .withLastUpdateDate(Instant.now())
                .build();

        ModuleItem<?> moduleItemTwo = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.QUANTITY)
                .withQuantity(0.0, 1.0)
                .withDisplayDecimal(false)
                .build();

        moduleItems.add(moduleItemOne);
        moduleItems.add(moduleItemTwo);

        content.setModuleItems(moduleItems);

        return content;
    }
}
