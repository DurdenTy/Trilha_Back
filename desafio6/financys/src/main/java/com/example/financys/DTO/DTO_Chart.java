package com.example.financys.DTO;


public class DTO_Chart {

    private String name;
    private String type;
    private String amount;

    public DTO_Chart(){}

    public DTO_Chart(String name, String type, String amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


}
