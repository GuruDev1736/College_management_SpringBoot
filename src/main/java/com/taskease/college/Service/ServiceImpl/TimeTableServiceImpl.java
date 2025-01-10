package com.taskease.college.Service.ServiceImpl;


import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Batch;
import com.taskease.college.Model.Department;
import com.taskease.college.Model.TimeTable;
import com.taskease.college.Model.Year;
import com.taskease.college.PayLoad.TimeTableDTO;
import com.taskease.college.Repository.BatchRepo;
import com.taskease.college.Repository.DepartmentRepo;
import com.taskease.college.Repository.TimeTableRepo;
import com.taskease.college.Repository.YearRepo;
import com.taskease.college.Service.TimeTableService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    private final YearRepo yearRepo;
    private final DepartmentRepo departmentRepo;
    private final BatchRepo batchRepo;
    private final TimeTableRepo timeTableRepo;
    private final ModelMapper modelMapper;

    public TimeTableServiceImpl(YearRepo yearRepo, DepartmentRepo departmentRepo, BatchRepo batchRepo, TimeTableRepo timeTableRepo, ModelMapper modelMapper) {
        this.yearRepo = yearRepo;
        this.departmentRepo = departmentRepo;
        this.batchRepo = batchRepo;
        this.timeTableRepo = timeTableRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public TimeTableDTO createTimeTable(TimeTableDTO timeTableDTO , int yearId , int departmentId , int batchId) {

        Year year = yearRepo.findById(yearId).orElseThrow(()-> new ResourceNotFoundException("Year","Id",yearId));
        Department department = departmentRepo.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department","Id",departmentId));
        Batch batch = batchRepo.findById(batchId).orElseThrow(()-> new ResourceNotFoundException("Batch","Id",batchId));

        TimeTable timeTable = modelMapper.map(timeTableDTO, TimeTable.class);
        timeTable.setYear(year);
        timeTable.setDepartment(department);
        timeTable.setBatch(batch);
        TimeTable saved = timeTableRepo.save(timeTable);
        return modelMapper.map(saved, TimeTableDTO.class);
    }

    @Override
    public TimeTableDTO getTimeTable(int id) {
        TimeTable timeTable = timeTableRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("TimeTable","id",id));
        return modelMapper.map(timeTable, TimeTableDTO.class);
    }

    @Override
    public List<TimeTableDTO> getAllTimeTable() {
        List<TimeTableDTO> timeTableDTOS = timeTableRepo.findAll().stream().map(timeTable -> modelMapper.map(timeTable, TimeTableDTO.class)).toList();
        return timeTableDTOS;
    }

    @Override
    public TimeTableDTO updateTimeTable(int id, TimeTableDTO timeTableDTO) {
        TimeTable timeTable = timeTableRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("TimeTable","id",id));
        timeTable.setTitle(timeTableDTO.getTitle());
        timeTable.setDescription(timeTableDTO.getDescription());
        timeTable.setDownloadUrl(timeTableDTO.getDownloadUrl());
        TimeTable updated = timeTableRepo.save(timeTable);
        return modelMapper.map(updated, TimeTableDTO.class);
    }

    @Override
    public void deleteTimeTable(int id) {
        TimeTable timeTable = timeTableRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("TimeTable","id",id));
        timeTableRepo.delete(timeTable);
    }

    @Override
    public List<TimeTableDTO> getTimeTableByCategory(String category) {
        List<TimeTableDTO> timeTableDTOS = timeTableRepo.findByCategory(category).stream().map(timeTable -> modelMapper.map(timeTable, TimeTableDTO.class)).toList();
        return timeTableDTOS;
    }

    @Override
    public List<TimeTableDTO> getTimeTableByAllDetails(int yearId, int departmentId, int batchId, String category) {
        List<TimeTableDTO> timeTableDTOS = timeTableRepo.findByDetails(yearId,departmentId,batchId,category).stream().map(timeTable -> modelMapper.map(timeTable, TimeTableDTO.class)).toList();
        return timeTableDTOS;
    }
}
