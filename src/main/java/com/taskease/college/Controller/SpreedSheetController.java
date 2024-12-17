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
        return ResponseEntity.ok(new ApiResponse<>("200","SpreedSheet Created Successfully",createdSpreedSheet));
    }
    
    @PutMapping("/update/{sheetId}")
    public ResponseEntity<ApiResponse<SpreedSheetDTO>> updateSpreedSheet(@RequestBody SpreedSheetDTO spreedSheetDTO , @PathVariable("sheetId") long sheetId){
        SpreedSheetDTO updatedSpreedSheet = this.spreedSheetService.updateSpreedSheet(spreedSheetDTO,sheetId);
        return ResponseEntity.ok(new ApiResponse<>("200","SpreedSheet Updated Successfully",updatedSpreedSheet));
    }
    
    @GetMapping("/{sheetId}")
    public ResponseEntity<ApiResponse<SpreedSheetDTO>> getSpreedSheet(@PathVariable("sheetId") long sheetId){
        SpreedSheetDTO spreedSheetDTO = this.spreedSheetService.getSpreedSheet(sheetId);
        return ResponseEntity.ok(new ApiResponse<>("200","SpreedSheet Retrieved Successfully",spreedSheetDTO));
    }
    
    @DeleteMapping("/delete/{sheetId}")
    public ResponseEntity<ApiResponse<String>> deleteSpreedSheet(@PathVariable("sheetId") long sheetId){
        this.spreedSheetService.deleteSpreedSheet(sheetId);
        return ResponseEntity.ok(new ApiResponse<>("200","Sheet Deleted Successfully",""));
    }
    
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<SpreedSheetDTO>>> getSpreedSheetsByCategory(@RequestParam("category") String category , @RequestParam("department") int departmentId) {
        List<SpreedSheetDTO> spreedSheets = this.spreedSheetService.getByCategory(category,departmentId);
        return ResponseEntity.ok(new ApiResponse<>("200", "SpreedSheets Retrieved Successfully", spreedSheets));
    }
}
