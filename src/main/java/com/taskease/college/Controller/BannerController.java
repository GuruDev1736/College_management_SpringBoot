package com.taskease.college.Controller;

import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.BannerDTO;
import com.taskease.college.Service.BannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {

    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<BannerDTO>> createBanner(@RequestBody BannerDTO bannerDTO) {
        BannerDTO banner = this.bannerService.createBanner(bannerDTO);
        return ResponseEntity.ok(new ApiResponse<>("200","Banner Created Successfully",banner));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<BannerDTO>>> getBanner() {
        List<BannerDTO> bannerDTOS = this.bannerService.getAllBanners();
        return ResponseEntity.ok(new ApiResponse<>("200","Banner List Fetched Successfully",bannerDTOS));
    }
}
