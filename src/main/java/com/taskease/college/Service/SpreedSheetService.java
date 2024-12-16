package com.taskease.college.Service;

import com.taskease.college.PayLoad.SpreedSheetDTO;

import java.util.List;

public interface SpreedSheetService {

    SpreedSheetDTO createSpreedSheet(SpreedSheetDTO spreedSheetDTO , int departmentId);
    SpreedSheetDTO updateSpreedSheet(SpreedSheetDTO spreedSheetDTO , long id);
    SpreedSheetDTO getSpreedSheet(long id);
    Void deleteSpreedSheet(long id);
    List<SpreedSheetDTO> getByCategory(String category , int departmentId);
}
