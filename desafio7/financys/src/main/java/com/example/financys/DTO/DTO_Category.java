package com.example.financys.DTO;


public class DTO_Category {

    private String name;
    private String description;

    public DTO_Category(){}

    public DTO_Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
