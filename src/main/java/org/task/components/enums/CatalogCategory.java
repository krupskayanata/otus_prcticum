package org.task.components.enums;

public enum CatalogCategory {

    TESTING("Тестирование");

    private String title;

    CatalogCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
