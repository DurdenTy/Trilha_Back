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
    @ManyToOne
    @JoinColumn(name = "CategotyId", referencedColumnName = "id")
    private Category categoryId;


}
