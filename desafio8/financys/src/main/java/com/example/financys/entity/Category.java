package com.example.financys.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Campo nome não pode ser vazio")
    @NotNull(message = "Campo nome não pode ser nulo")
    @Size(min = 3, max = 15, message = "Nome precisa ter min 3 a 15 caracteres")
    private String name;
    @NotBlank(message = "Campo de descrição não pode ser vazio")
    @NotNull(message = "Campo de descrição não pode ser nulo")
    @Size(min = 15, max = 50, message = "Descrição precisa ter min 15 a 50 caracteres")
    private String description;

}