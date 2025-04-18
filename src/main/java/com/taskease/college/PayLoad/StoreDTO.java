package com.taskease.college.PayLoad;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private int id;
    private String category;
    private Boolean status = false;
}
