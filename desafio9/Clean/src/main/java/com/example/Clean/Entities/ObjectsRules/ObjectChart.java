package com.example.Clean.Entities.ObjectsRules;

import lombok.*;

@Getter
@Setter
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
