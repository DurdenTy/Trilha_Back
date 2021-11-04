package com.desafio10.Financys.Domain.Entities;


import com.desafio10.Financys.Domain.ValueObject.Types;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String Date;
    private String amount;
    @Enumerated(EnumType.STRING)
    private Types Types;
    private Boolean paid;
    private Long categoryId;

}
