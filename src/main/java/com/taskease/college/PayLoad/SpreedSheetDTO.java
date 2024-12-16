package com.taskease.college.PayLoad;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpreedSheetDTO {
    private long id;
    private Date creationDate = new Date();
    private String title ;
    private String description ;
    private String link ;
    private String imageLink;
    private String category;
}
