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
import java.util.stream.Collectors;

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
        Bonafide bonafide = bonafideRepo.findById(bonafideId).orElseThrow(() -> new ResourceNotFoundException("Bonafide","Id",bonafideId));
        return modelMapper.map(bonafide,BonafideDTO.class);
    }

    @Override
    public List<BonafideDTO> getAllBonafide() {
        List<BonafideDTO> bonafideList = bonafideRepo.findAll().stream().map(x -> modelMapper.map(x,BonafideDTO.class)).toList();
        return bonafideList;
    }

    @Override
    public List<BonafideDTO> getBonafideByStudent(long studentId) {

        Student student = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student","Id",studentId));
        List<BonafideDTO> list = bonafideRepo.findByStudent(student).stream().map(x -> modelMapper.map(x,BonafideDTO.class)).toList();
        return list;
    }

    @Override
    public void deleteBonafide(long bonafideId) {
        Bonafide bonafide = bonafideRepo.findById(bonafideId).orElseThrow(() -> new ResourceNotFoundException("Bonafide","Id",bonafideId));
        bonafideRepo.deletebonafideById(bonafideId);
    }
}
