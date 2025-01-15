package com.taskease.college.Service;

import com.taskease.college.PayLoad.BannerDTO;

import java.util.List;

public interface BannerService {

    BannerDTO createBanner(BannerDTO bannerDTO);
    List<BannerDTO> getAllBanners();
    void deleteBanner(int bannerId);
}
