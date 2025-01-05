package com.taskease.college.Service;

import com.taskease.college.PayLoad.YearDTO;

import java.util.List;

public interface YearService {

    YearDTO createYear(YearDTO yearDTO);
    List<YearDTO> getYears();
    YearDTO getYear(int year);
    void deleteYear(int year);
}
