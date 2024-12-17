package com.taskease.college.Service;


import com.taskease.college.PayLoad.AttendanceDTO;
import com.taskease.college.PayLoad.SpreedSheetDTO;

import java.util.List;

public interface AttendanceService {

    AttendanceDTO createAttendanceSheet(AttendanceDTO attendanceDTO , int departmentId , long userId);
    AttendanceDTO updateAttendanceSheet(AttendanceDTO attendanceDTO , long id);
    AttendanceDTO getAttendanceSheet(long id);
    Void deleteAttendanceSheet(long id);
    List<AttendanceDTO> getByCategory(String category , int departmentId);
}
