package com.taskease.college.Controller;

import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.AttendanceDTO;
import com.taskease.college.Service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


    @PostMapping("/create/{departmentId}/{userId}")
    public ResponseEntity<ApiResponse<AttendanceDTO>> createAttendanceSheet(@RequestBody AttendanceDTO attendanceDTO , @PathVariable("departmentId") int departmentId , @PathVariable("userId") long userId){
        AttendanceDTO createdSpreedSheet = this.attendanceService.createAttendanceSheet(attendanceDTO,departmentId,userId);
        return ResponseEntity.ok(new ApiResponse<>("200","Attendance Created Successfully",createdSpreedSheet));
    }

    @PutMapping("/update/{sheetId}")
    public ResponseEntity<ApiResponse<AttendanceDTO>> updateSpreedSheet(@RequestBody AttendanceDTO attendanceDTO , @PathVariable("sheetId") long sheetId){
        AttendanceDTO updatedSpreedSheet = this.attendanceService.updateAttendanceSheet(attendanceDTO,sheetId);
        return ResponseEntity.ok(new ApiResponse<>("200","Attendance Sheet Updated Successfully",updatedSpreedSheet));
    }

    @GetMapping("/{sheetId}")
    public ResponseEntity<ApiResponse<AttendanceDTO>> getSpreedSheet(@PathVariable("sheetId") long sheetId){
        AttendanceDTO spreedSheetDTO = this.attendanceService.getAttendanceSheet(sheetId);
        return ResponseEntity.ok(new ApiResponse<>("200","Attendance Sheet Retrieved Successfully",spreedSheetDTO));
    }

    @DeleteMapping("/delete/{sheetId}")
    public ResponseEntity<ApiResponse<String>> deleteSpreedSheet(@PathVariable("sheetId") long sheetId){
        this.attendanceService.deleteAttendanceSheet(sheetId);
        return ResponseEntity.ok(new ApiResponse<>("200","Attendance Sheet Deleted Successfully",""));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<AttendanceDTO>>> getSpreedSheetsByCategory(@RequestParam("category") String category , @RequestParam("department") int departmentId) {
        List<AttendanceDTO> spreedSheets = this.attendanceService.getByCategory(category,departmentId);
        return ResponseEntity.ok(new ApiResponse<>("200", "Attendance Sheet Retrieved Successfully", spreedSheets));
    }
}
