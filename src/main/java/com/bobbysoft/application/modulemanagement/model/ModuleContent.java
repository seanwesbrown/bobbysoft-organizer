package com.bobbysoft.application.modulemanagement.model;

import java.util.LinkedList;

public class ModuleContent {
    public LinkedList<ModuleItem<?>> items;

    public LinkedList<ModuleItem<?>> getModuleItems() {
        return items;
    }

    public void setModuleItems(LinkedList<ModuleItem<?>> items) {
        this.items = items;
    }
}
