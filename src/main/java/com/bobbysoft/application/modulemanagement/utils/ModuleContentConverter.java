package com.bobbysoft.application.modulemanagement.utils;

import com.bobbysoft.application.base.utils.ObjectMapperBuilder;
import com.bobbysoft.application.modulemanagement.model.ModuleContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ModuleContentConverter implements AttributeConverter<ModuleContent, String> {
    Logger logger = LogManager.getLogger();

    @Override
    public String convertToDatabaseColumn(ModuleContent moduleContent) {
        ObjectMapper objectMapper = new ObjectMapperBuilder().build();
        String moduleContentJson = null;

        try {
            moduleContentJson = objectMapper.writeValueAsString(moduleContent);
        } catch (final JsonProcessingException e) {
            logger.error("Unable to convert ModuleComponent to json", e);
        }

        return moduleContentJson;
    }

    @Override
    public ModuleContent convertToEntityAttribute(String moduleContentJson) {
        ObjectMapper objectMapper = new ObjectMapperBuilder().build();
        ModuleContent moduleContent = null;

        try {
            moduleContent = objectMapper.readValue(moduleContentJson, ModuleContent.class);
        } catch (final IOException e) {
            logger.error("Unable to convert json to ModuleComponent", e);
        }

        return moduleContent;
    }
}
