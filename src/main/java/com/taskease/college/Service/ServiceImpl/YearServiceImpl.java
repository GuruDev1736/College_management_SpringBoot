package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Year;
import com.taskease.college.PayLoad.YearDTO;
import com.taskease.college.Repository.YearRepo;
import com.taskease.college.Service.YearService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class YearServiceImpl implements YearService {

    private final ModelMapper modelMapper;
    private final YearRepo yearRepo;

    public YearServiceImpl(ModelMapper modelMapper, YearRepo yearRepo) {
        this.modelMapper = modelMapper;
        this.yearRepo = yearRepo;
    }

    @Override
    public YearDTO createYear(YearDTO yearDTO) {
        Year year = modelMapper.map(yearDTO, Year.class);
        Year saved = yearRepo.save(year);
        return modelMapper.map(saved, YearDTO.class);
    }

    @Override
    public List<YearDTO> getYears() {
        List<YearDTO> years = yearRepo.findAll().stream().map(year -> modelMapper.map(year, YearDTO.class)).toList();
        return years;
    }

    @Override
    public YearDTO getYear(int year) {
        Year year1 = yearRepo.findById(year).orElseThrow(()-> new ResourceNotFoundException("Year","Id",year));
        return modelMapper.map(year1, YearDTO.class);
    }

    @Override
    public void deleteYear(int year) {
        Year year1 = yearRepo.findById(year).orElseThrow(()-> new ResourceNotFoundException("Year","Id",year));
        yearRepo.deleteYearById(year);
    }
}
