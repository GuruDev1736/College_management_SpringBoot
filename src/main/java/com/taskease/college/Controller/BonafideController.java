package com.taskease.college.Controller;


import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.BonafideDTO;
import com.taskease.college.Service.BonafideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
