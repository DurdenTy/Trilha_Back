package com.example.financys.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DtoChart {

    private String name;
    private String description;
    private String type;
    private String date;
    private String amount;
    private Boolean paid;

}
