package com.taskease.college.Controller;


import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.BatchDTO;
import com.taskease.college.Service.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<BatchDTO>>> getAllBatches(){
        List<BatchDTO> batchDTOS = batchService.getBatchList();
        return ResponseEntity.ok(new ApiResponse<>("200","Batch List Fetched Successfully",batchDTOS));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<ApiResponse<List<BatchDTO>>> getBatch(@PathVariable("departmentId") int departmentId){
        List<BatchDTO> batchDTOS = batchService.getBatchByDepartmentList(departmentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Batch List Fetched Successfully",batchDTOS));
    }

    @DeleteMapping("/{batchId}")
    public ResponseEntity<ApiResponse<String>> deleteBatch(@PathVariable("batchId") int batchId){
        this.batchService.deleteBatch(batchId);
        return ResponseEntity.ok(new ApiResponse<>("200","Batch Deleted Successfully",""));
    }
}
