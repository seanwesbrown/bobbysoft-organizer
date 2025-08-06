package com.bobbysoft.application.base.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperBuilder {
    boolean failOnUnknownProperties = false;

    public ObjectMapperBuilder failOnUnknownPropertes(boolean failOnUnknownProperties) {
        this.failOnUnknownProperties = failOnUnknownProperties;
        return this;
    }

    public ObjectMapper build() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);

        return objectMapper;
    }
}
