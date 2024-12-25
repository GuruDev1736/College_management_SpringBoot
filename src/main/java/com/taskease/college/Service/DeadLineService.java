package com.taskease.college.Service;

import com.taskease.college.PayLoad.DeadLineDTO;

import java.util.List;

public interface DeadLineService {

    DeadLineDTO createDeadLine(DeadLineDTO deadLineDTO , long userId);
    List<DeadLineDTO> getAllDeadLines();
    void deleteDeadLine(long deadLineId);
    DeadLineDTO updateDeadLine(long deadLineId, DeadLineDTO deadLineDTO);
}
