package com.taskease.college.Controller;

import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.SpreedSheetDTO;
import com.taskease.college.Service.SpreedSheetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spreadsheet")
public class SpreedSheetController {
    
    private final SpreedSheetService spreedSheetService ;

    public SpreedSheetController(SpreedSheetService spreedSheetService) {
        this.spreedSheetService = spreedSheetService;
    }

    @PostMapping("/create/{departmentId}/{userId}")
    public ResponseEntity<ApiResponse<SpreedSheetDTO>> createSpreedSheet(@RequestBody SpreedSheetDTO spreedSheetDTO , @PathVariable("departmentId") int departmentId , @PathVariable("userId") long userId){
        SpreedSheetDTO createdSpreedSheet = this.spreedSheetService.createSpreedSheet(spreedSheetDTO,departmentId,userId);
        return ResponseEntity.ok(new ApiResponse<>("200","Data Added Successfully",createdSpreedSheet));
    }
    
    @PutMapping("/update/{sheetId}")
    public ResponseEntity<ApiResponse<SpreedSheetDTO>> updateSpreedSheet(@RequestBody SpreedSheetDTO spreedSheetDTO , @PathVariable("sheetId") long sheetId){
        SpreedSheetDTO updatedSpreedSheet = this.spreedSheetService.updateSpreedSheet(spreedSheetDTO,sheetId);
        return ResponseEntity.ok(new ApiResponse<>("200","Data Updated Successfully",updatedSpreedSheet));
    }
    
    @GetMapping("/{sheetId}")
    public ResponseEntity<ApiResponse<SpreedSheetDTO>> getSpreedSheet(@PathVariable("sheetId") long sheetId){
        SpreedSheetDTO spreedSheetDTO = this.spreedSheetService.getSpreedSheet(sheetId);
        return ResponseEntity.ok(new ApiResponse<>("200","Data Retrieved Successfully",spreedSheetDTO));
    }
    
    @DeleteMapping("/delete/{sheetId}")
    public ResponseEntity<ApiResponse<String>> deleteSpreedSheet(@PathVariable("sheetId") long sheetId){
        this.spreedSheetService.deleteSpreedSheet(sheetId);
        return ResponseEntity.ok(new ApiResponse<>("200","Data Deleted Successfully",""));
    }
    
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<SpreedSheetDTO>>> getSpreedSheetsByCategoryAndDepartment(@RequestParam("category") String category , @RequestParam("department") int departmentId) {
        List<SpreedSheetDTO> spreedSheets = this.spreedSheetService.getByCategoryAndDepartment(category,departmentId);
        return ResponseEntity.ok(new ApiResponse<>("200", "Data Retrieved Successfully", spreedSheets));
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<SpreedSheetDTO>>> getSpreedSheetByCategory(@RequestParam("category") String category) {
        List<SpreedSheetDTO> spreedSheets = this.spreedSheetService.getByCategory(category);
        return ResponseEntity.ok(new ApiResponse<>("200", "Data Retrieved Successfully", spreedSheets));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<SpreedSheetDTO>>> getSpreadSheetByUserAndCategory(@RequestParam("category") String category , @RequestParam("department") int departmentId , @RequestParam("userId") long userId) {
        List<SpreedSheetDTO> spreedSheets = this.spreedSheetService.getByCategoryAndByUser(category,departmentId,userId);
        return ResponseEntity.ok(new ApiResponse<>("200", "Data Retrieved Successfully", spreedSheets));
    }

    @PostMapping("/create/budget/{userId}")
    public ResponseEntity<ApiResponse<SpreedSheetDTO>> createBudgetSheet(@RequestBody SpreedSheetDTO spreedSheetDTO ,@PathVariable("userId") long userId){
        SpreedSheetDTO createdSpreedSheet = this.spreedSheetService.createOfficeBudget(spreedSheetDTO,userId);
        return ResponseEntity.ok(new ApiResponse<>("200","Document Added Successfully",createdSpreedSheet));
    }

    @PostMapping("/create/penalty/{userId}")
    public ResponseEntity<ApiResponse<SpreedSheetDTO>> createPenalty(@RequestBody SpreedSheetDTO spreedSheetDTO ,@PathVariable("userId") long userId){
        SpreedSheetDTO createdSpreedSheet = this.spreedSheetService.createPenalty(spreedSheetDTO,userId);
        return ResponseEntity.ok(new ApiResponse<>("200","Penalty Added Successfully",createdSpreedSheet));
    }
}
