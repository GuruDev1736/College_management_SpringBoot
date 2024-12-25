package com.taskease.college.Service.ServiceImpl;


import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.DeadLine;
import com.taskease.college.Model.User;
import com.taskease.college.PayLoad.DeadLineDTO;
import com.taskease.college.Repository.DeadlineRepo;
import com.taskease.college.Repository.UserRepo;
import com.taskease.college.Service.DeadLineService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeadLineServiceImpl implements DeadLineService {

    private final DeadlineRepo deadlineRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;

    public DeadLineServiceImpl(DeadlineRepo deadlineRepo, ModelMapper modelMapper, UserRepo userRepo) {
        this.deadlineRepo = deadlineRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }

    @Override
    public DeadLineDTO createDeadLine(DeadLineDTO deadLineDTO , long userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        DeadLine deadLine = this.modelMapper.map(deadLineDTO, DeadLine.class);
        deadLine.setUser(user);

        DeadLine savedDeadLine = this.deadlineRepo.save(deadLine);
        return modelMapper.map(savedDeadLine, DeadLineDTO.class);
    }

    @Override
    public List<DeadLineDTO> getAllDeadLines() {
        List<DeadLineDTO> deadLineDTOS = this.deadlineRepo.findAll().stream().map(d -> modelMapper.map(d, DeadLineDTO.class)).toList();
        return deadLineDTOS;
    }

    @Override
    public void deleteDeadLine(long deadLineId) {
        DeadLine deadLine = this.deadlineRepo.findById(deadLineId).orElseThrow(()-> new ResourceNotFoundException("DeadLine","Id",deadLineId));
        this.deadlineRepo.delete(deadLine);
    }

    @Override
    public DeadLineDTO updateDeadLine(long deadLineId, DeadLineDTO deadLineDTO) {
        DeadLine deadLine = this.deadlineRepo.findById(deadLineId).orElseThrow(()-> new ResourceNotFoundException("DeadLine","Id",deadLineId));
        deadLine.setTitle(deadLineDTO.getTitle());
        deadLine.setDescription(deadLineDTO.getDescription());
        deadLine.setStartDate(deadLineDTO.getStartDate());
        deadLine.setEndDate(deadLineDTO.getEndDate());
        DeadLine updatedDeadLine = this.deadlineRepo.save(deadLine);
        return modelMapper.map(updatedDeadLine, DeadLineDTO.class);
    }
}
