package com.taskease.college.Service;

import com.taskease.college.PayLoad.SpreedSheetDTO;

import java.util.List;

public interface SpreedSheetService {

    SpreedSheetDTO createSpreedSheet(SpreedSheetDTO spreedSheetDTO , int departmentId , long userId);
    SpreedSheetDTO updateSpreedSheet(SpreedSheetDTO spreedSheetDTO , long id);
    SpreedSheetDTO getSpreedSheet(long id);
    Void deleteSpreedSheet(long id);
    List<SpreedSheetDTO> getByCategoryAndDepartment(String category , int departmentId);
    List<SpreedSheetDTO> getByCategoryAndByUser(String category , int departmentId , long userId);
    List<SpreedSheetDTO> getByCategory(String category);
    SpreedSheetDTO createOfficeBudget(SpreedSheetDTO spreedSheetDTO,long userId);
    SpreedSheetDTO createPenalty(SpreedSheetDTO spreedSheetDTO,long userId);
}
