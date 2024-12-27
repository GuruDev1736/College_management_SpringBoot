package com.taskease.college.Controller;

import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.StockDTO;
import com.taskease.college.Service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<StockDTO>> createStock(@RequestBody StockDTO stockDTO) {
        StockDTO stockDTO1 = stockService.createStock(stockDTO);
        return ResponseEntity.ok(new ApiResponse<>("200","Stock Added SuccessFully", stockDTO1));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<StockDTO>>> getAllStock() {
        stockService.getStocks();
        return ResponseEntity.ok(new ApiResponse<>("200","Stock List Fetched Successfully", stockService.getStocks()));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<String>> updateStock(@PathVariable int id) {
        String status = stockService.updateToggle(id);
        return ResponseEntity.ok(new ApiResponse<>("200","Status Updated Successfully", status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStock(@PathVariable int id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok(new ApiResponse<>("200","Stock Deleted Successfully",""));
    }
}
