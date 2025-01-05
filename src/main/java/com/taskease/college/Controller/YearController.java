package com.taskease.college.Controller;

import com.taskease.college.Model.Year;
import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.YearDTO;
import com.taskease.college.Service.YearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/year")
public class YearController {

    private final YearService yearService;

    public YearController(YearService yearService) {
        this.yearService = yearService;
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<YearDTO>> createYear(@RequestBody YearDTO yearDTO) {
        YearDTO year = yearService.createYear(yearDTO);
        return ResponseEntity.ok(new ApiResponse<>("200","Year Created Successfully",year));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<YearDTO>>> getYears() {
        List<YearDTO> years = yearService.getYears();
        return ResponseEntity.ok(new ApiResponse<>("200","Years List Fetched Successfully",years));
    }

    @GetMapping("/{yearId}")
    public ResponseEntity<ApiResponse<YearDTO>> getYearById(@PathVariable int yearId) {
        YearDTO yearDTO = yearService.getYear(yearId);
        return ResponseEntity.ok(new ApiResponse<>("200","Year Found",yearDTO));
    }

    @DeleteMapping("/{yearId}")
    public ResponseEntity<ApiResponse<String>> deleteYear(@PathVariable int yearId) {
        yearService.deleteYear(yearId);
        return ResponseEntity.ok(new ApiResponse<>("200","Year Deleted Successfully",""));
    }
}
