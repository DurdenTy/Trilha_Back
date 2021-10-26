package com.example.DDD.Domain.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = " Campo nome não pode ser vazio")
    @NotNull(message = " Campo nome não pode ser nulo")
    @Size(min = 3, max = 45, message = " Nome precisa ter min 3 a 45 caracteres")
    private String name;
    @NotBlank(message = "Campo de descrição não pode ser vazia")
    @NotNull(message = "Campo de descrição não pode ser nula")
    @Size(min = 15, max = 150, message = "Descrição precisa ter min 15 a 150 caracteres")
    private String description;
    @NotBlank(message = "Campo tipo não pode ser vazia")
    @NotNull(message = "Campo tipo não pode ser nula")
    @Size(min = 3, max = 10, message = " Tipo precisa ter min 3 a 10 caracteres")
    private String type;
    @NotBlank(message = "Valor montante não pode ser vazio")
    @NotNull(message = "Valor  montante não pode ser nulo")
    @Min(value = 0, message = "Valor mínimo permitido é 0")
    private String amount;
    @NotBlank(message = "Campo data não pode ser vazia")
    @NotNull(message = "Campo data não pode ser nula")
    private String date;
    @NotNull(message = "O campo pago não pode ser nulo, aceita apenas sim ou não")
    private boolean paid;
    private long categoryId;


}