package com.taskease.college.Service;

import com.taskease.college.PayLoad.TimeTableDTO;

import java.util.List;

public interface TimeTableService {

    TimeTableDTO createTimeTable(TimeTableDTO timeTableDTO , int yearId , int departmentId , int batchId);
    TimeTableDTO getTimeTable(int id);
    List<TimeTableDTO> getAllTimeTable();
    TimeTableDTO updateTimeTable(int id, TimeTableDTO timeTableDTO);
    void deleteTimeTable(int id);
    List<TimeTableDTO> getTimeTableByCategory(String category);
    List<TimeTableDTO> getTimeTableByAllDetails(int yearId , int departmentId , int batchId, String category);

}
