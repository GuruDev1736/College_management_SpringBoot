package com.taskease.college.Controller;


import com.google.protobuf.Api;
import com.taskease.college.Model.Bonafide;
import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.BonafideDTO;
import com.taskease.college.Service.BonafideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bonafide")
public class BonafideController {

    private final BonafideService bonafideService;

    public BonafideController(BonafideService bonafideService) {
        this.bonafideService = bonafideService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<ApiResponse<BonafideDTO>> createBonafide(@RequestBody BonafideDTO bonafideDTO , @PathVariable("id") long studentId)
    {
        BonafideDTO bonafide = bonafideService.createBonafide(bonafideDTO, studentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Bonafide Request Sent Successfully",bonafide));
    }

    @GetMapping("/{bonafideId}")
    public ResponseEntity<ApiResponse<BonafideDTO>> getBonafideById(@PathVariable long bonafideId)
    {
        BonafideDTO bonafideDTO = bonafideService.getById(bonafideId);
        return ResponseEntity.ok(new ApiResponse<>("200","Bonafide info Found",bonafideDTO));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<BonafideDTO>>> getAllBonafide()
    {
        List<BonafideDTO> bonafide = bonafideService.getAllBonafide();
        return ResponseEntity.ok(new ApiResponse<>("200","Bonafide Fetched Successfully",bonafide));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<BonafideDTO>>> getAllBonafideByStudent(@PathVariable long studentId)
    {
        List<BonafideDTO> bonafideDTOS = bonafideService.getBonafideByStudent(studentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Bonafide Fetched Successfully",bonafideDTOS));
    }

    @DeleteMapping("/{bonafideId}")
    public ResponseEntity<ApiResponse<String>> deleteBonafide(@PathVariable long bonafideId)
    {
        bonafideService.deleteBonafide(bonafideId);
        return ResponseEntity.ok(new ApiResponse<>("200","Bonafide Deleted Successfully",""));
    }
}
