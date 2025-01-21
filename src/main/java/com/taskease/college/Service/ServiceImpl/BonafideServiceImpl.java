package com.taskease.college.Service.ServiceImpl;


import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Bonafide;
import com.taskease.college.Model.Student;
import com.taskease.college.PayLoad.BonafideDTO;
import com.taskease.college.Repository.BonafideRepo;
import com.taskease.college.Repository.StudentRepo;
import com.taskease.college.Service.BonafideService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class BonafideServiceImpl implements BonafideService {

    private final ModelMapper modelMapper;
    private final BonafideRepo bonafideRepo;
    private final StudentRepo studentRepo;

    public BonafideServiceImpl(ModelMapper modelMapper, BonafideRepo bonafideRepo, StudentRepo studentRepo) {
        this.modelMapper = modelMapper;
        this.bonafideRepo = bonafideRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public BonafideDTO createBonafide(BonafideDTO bonafideDTO, long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));

        Bonafide bonafide = modelMapper.map(bonafideDTO,Bonafide.class);
        bonafide.setStudent(student);
        Bonafide saved = bonafideRepo.save(bonafide);
        return modelMapper.map(saved, BonafideDTO.class);
    }

    @Override
    public BonafideDTO getById(long bonafideId) {
        return null;
    }

    @Override
    public List<BonafideDTO> getAllBonafide() {
        return List.of();
    }

    @Override
    public List<BonafideDTO> getBonafideByStudent(long studentId) {
        return List.of();
    }

    @Override
    public void deleteBonafide(long bonafideId) {

    }
}
