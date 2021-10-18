package com.example.financys.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "nome não pode ser vazio ou nulo")
    @Size(min = 3, max = 45, message = "min 3 a 45 caracteres")
    private String name;
    @NotBlank(message = "descrição não pode ser vazia ou nula")
    @Size(min = 15, max = 150, message = "min 15 a 150 caracteres")
    private String description;
    @NotBlank(message = "tipo não pode ser nula ou vazia")
    @Size(min = 3, max = 10, message = "min 3 a 10 caracteres")
    private String type;
    @NotBlank(message = "montante não pode ser nulo ou vazio")
    @Min(value = 0, message = "Valor mínimo permitido é 0")
    private String amount;
    @NotBlank(message = "data não pode ser nula ou vazia")
    private String date;
    @NotNull(message = "pago não pode ser nulo ou vazio")
    private boolean paid;

    private long categoryId;

    public Entry(){}

    public Entry(long id, String name, String description, String type,
                 String amount, String date, boolean paid, long categoryId){

        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.paid = paid;
        this.categoryId = categoryId;
    }

    public Entry(String name, String type, String amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getPaid() {
        return paid;
    }

    public void isPaid(boolean paid) {
        this.paid = paid;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", paid=" + paid +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}