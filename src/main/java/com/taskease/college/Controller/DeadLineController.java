package com.taskease.college.Controller;

import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.DeadLineDTO;
import com.taskease.college.Service.DeadLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deadline")
public class DeadLineController {

    private final DeadLineService deadLineService;

    public DeadLineController(DeadLineService deadLineService) {
        this.deadLineService = deadLineService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<ApiResponse<DeadLineDTO>> createDeadLine(@RequestBody DeadLineDTO deadLineDTO , @PathVariable long userId) {
        DeadLineDTO result = deadLineService.createDeadLine(deadLineDTO, userId);
        return ResponseEntity.ok(new ApiResponse<>("200","Deadline created", result));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<DeadLineDTO>>> getAllDeadLines() {
        List<DeadLineDTO> result = deadLineService.getAllDeadLines();
        return ResponseEntity.ok(new ApiResponse<>("200","Deadline list Fetched Successfully", result));
    }

    @DeleteMapping("/{deadlineId}")
    public ResponseEntity<ApiResponse<String>> deleteDeadLine(@PathVariable long deadlineId) {
        this.deadLineService.deleteDeadLine(deadlineId);  
        return ResponseEntity.ok(new ApiResponse<>("200","Deadline deleted", ""));
    }

    @PutMapping("/update/{deadlineId}")
    public ResponseEntity<ApiResponse<DeadLineDTO>> updateDeadLine(@RequestBody DeadLineDTO deadLineDTO , @PathVariable long deadlineId) {
        DeadLineDTO deadLineDTO1  = this.deadLineService.updateDeadLine(deadlineId, deadLineDTO);
        return ResponseEntity.ok(new ApiResponse<>("200","DeadLine Updated Successfully", deadLineDTO1));
    }
}
