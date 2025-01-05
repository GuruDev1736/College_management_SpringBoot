package com.taskease.college.Controller;

import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.StoreDTO;
import com.taskease.college.Service.ServiceImpl.StoreServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreServiceImpl storeService;

    public StoreController(StoreServiceImpl storeService) {
        this.storeService = storeService;
    }

    @PutMapping("/toggle-status")
    public ResponseEntity<ApiResponse<String>> toggleStoreStatus(@RequestParam("category") String category) {
        String status = storeService.updateStatus(category);
        return ResponseEntity.ok(new ApiResponse<>("200","Success",status));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<StoreDTO>> addStore(@RequestBody StoreDTO storeRequestDTO) {
        StoreDTO store = storeService.createStore(storeRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>("200","success",store));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<StoreDTO>>> getAllStores() {
        List<StoreDTO> list = storeService.getStoreList();
        return ResponseEntity.ok(new ApiResponse<>("200","Store List Fetched Successfully",list));
    }

    @DeleteMapping("/")
    public ResponseEntity<ApiResponse<String>> deleteStore(@RequestParam("category") String category) {
        storeService.deleteStore(category);
        return ResponseEntity.ok(new ApiResponse<>("200","Store Deleted Successfully",""));
    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<Boolean>> getStoreStatus(@RequestParam("category") String category) {
        StoreDTO storeDTO = this.storeService.getStore(category);
        return ResponseEntity.ok(new ApiResponse<>("200","success",storeDTO.getStatus()));
    }
}
