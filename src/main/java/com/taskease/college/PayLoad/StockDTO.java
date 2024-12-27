package com.taskease.college.PayLoad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private int id;
    private String title;
    private String description;
    private String price;
    private Boolean status = true;
}
