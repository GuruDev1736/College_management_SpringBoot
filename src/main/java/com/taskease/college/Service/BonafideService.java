package com.taskease.college.Service;

import com.taskease.college.PayLoad.BonafideDTO;

import java.util.List;

public interface BonafideService {

    BonafideDTO createBonafide(BonafideDTO bonafideDTO , long studentId);
    BonafideDTO getById(long bonafideId);
    List<BonafideDTO> getAllBonafide();
    List<BonafideDTO> getBonafideByStudent(long studentId);
    void deleteBonafide(long bonafideId);

}
