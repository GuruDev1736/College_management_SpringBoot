package com.taskease.college.Controller;


import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.BatchDTO;
import com.taskease.college.Service.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/create/{departmentId}")
    public ResponseEntity<ApiResponse<BatchDTO>> createBatch(@RequestBody BatchDTO batchDTO , @PathVariable("departmentId") int departmentId){
        BatchDTO createdBatch = this.batchService.createBatch(batchDTO,departmentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Batch Created Successfully",createdBatch));
    }
}
