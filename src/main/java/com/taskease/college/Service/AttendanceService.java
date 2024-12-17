package com.taskease.college.Service;


import com.taskease.college.PayLoad.AttendanceDTO;
import com.taskease.college.PayLoad.SpreedSheetDTO;

import java.util.List;

public interface AttendanceService {

    AttendanceDTO createSpreedSheet(AttendanceDTO attendanceDTO , int departmentId);
    AttendanceDTO updateSpreedSheet(AttendanceDTO attendanceDTO , long id);
    AttendanceDTO getSpreedSheet(long id);
    Void deleteSpreedSheet(long id);
    List<AttendanceDTO> getByCategory(String category , int departmentId);
}
