package com.example.DDD.Domain.ValueObject;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ObjectChart {

    private String name;
    private String description;
    private String type;
    private String date;
    private String amount;
    private Boolean paid;

}
