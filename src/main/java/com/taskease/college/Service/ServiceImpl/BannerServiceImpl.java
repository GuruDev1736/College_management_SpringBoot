package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Model.Banner;
import com.taskease.college.PayLoad.BannerDTO;
import com.taskease.college.Repository.BannerRepo;
import com.taskease.college.Service.BannerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepo bannerRepo;
    private final ModelMapper modelMapper;

    public BannerServiceImpl(BannerRepo bannerRepo, ModelMapper modelMapper) {
        this.bannerRepo = bannerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public BannerDTO createBanner(BannerDTO bannerDTO) {
        Banner banner = modelMapper.map(bannerDTO, Banner.class);
        Banner save = this.bannerRepo.save(banner);
        return modelMapper.map(save, BannerDTO.class);
    }

    @Override
    public List<BannerDTO> getAllBanners() {
        List<BannerDTO> banners = bannerRepo.findAll().stream().map(banner -> modelMapper.map(banner, BannerDTO.class)).toList();
        return banners;
    }
}
