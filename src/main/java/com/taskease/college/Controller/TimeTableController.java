package com.taskease.college.Controller;


import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.TimeTableDTO;
import com.taskease.college.Service.TimeTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timetable")
public class TimeTableController {

    private final TimeTableService timeTableService;

    public TimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TimeTableDTO>> createTimeTable(@RequestBody TimeTableDTO timeTableDTO, @RequestParam("year") int year , @RequestParam("department") int department , @RequestParam("batch") int batch) {
        TimeTableDTO timeTable = timeTableService.createTimeTable(timeTableDTO, year, department, batch);
        return ResponseEntity.ok(new ApiResponse<>("200","Time Table Created", timeTable));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TimeTableDTO>> updateTimeTable(@RequestBody TimeTableDTO timeTableDTO , @PathVariable int id) {
        TimeTableDTO timeTableDTO1 = timeTableService.updateTimeTable(id ,timeTableDTO);
        return ResponseEntity.ok(new ApiResponse<>("200","Time Table Updated", timeTableDTO1));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TimeTableDTO>> getTimeTable(@PathVariable int id) {
        TimeTableDTO timeTableDTO = timeTableService.getTimeTable(id);
        return ResponseEntity.ok(new ApiResponse<>("200","Time Table Found", timeTableDTO));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<TimeTableDTO>>> getTimeTables() {
        List<TimeTableDTO> timeTableDTOS = timeTableService.getAllTimeTable();
        return ResponseEntity.ok(new ApiResponse<>("200","Time Table Found", timeTableDTOS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTimeTable(@PathVariable int id) {
        timeTableService.deleteTimeTable(id);
        return ResponseEntity.ok(new ApiResponse<>("200","Time Table Deleted",""));
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<TimeTableDTO>>> getTimeTablesByCategory(@RequestParam("category") String category) {
        List<TimeTableDTO> timeTableDTOS = timeTableService.getTimeTableByCategory(category);
        return ResponseEntity.ok(new ApiResponse<>("200","Time Table Found", timeTableDTOS));
    }

    @GetMapping("/details")
    public ResponseEntity<ApiResponse<List<TimeTableDTO>>> getAllTimeTablesByDetails(@RequestParam("year") int yearId , @RequestParam("department") int departmentId , @RequestParam("batch") int batchId , @RequestParam("category") String category) {
        List<TimeTableDTO> timeTableDTOS = timeTableService.getTimeTableByAllDetails(yearId, departmentId, batchId, category);
        return ResponseEntity.ok(new ApiResponse<>("200","Time Table Found", timeTableDTOS));
    }
}

