package com.taskease.college.PayLoad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerDTO {

    private int id;
    private String title;
    private String imageLink;
    private Date creationDate = new Date();
}
