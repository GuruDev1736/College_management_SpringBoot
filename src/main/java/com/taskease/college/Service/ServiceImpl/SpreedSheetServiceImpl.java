package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Department;
import com.taskease.college.Model.Faculty.SpreedSheet;
import com.taskease.college.Model.User;
import com.taskease.college.PayLoad.SpreedSheetDTO;
import com.taskease.college.Repository.DepartmentRepo;
import com.taskease.college.Repository.SpreedSheetRepo;
import com.taskease.college.Repository.UserRepo;
import com.taskease.college.Service.SpreedSheetService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpreedSheetServiceImpl implements SpreedSheetService {

    private final SpreedSheetRepo spreedSheetRepo;
    private final ModelMapper modelMapper;
    private final DepartmentRepo departmentRepo ;
    private final UserRepo userRepo;
    private final EntityManager entityManager;

    public SpreedSheetServiceImpl(SpreedSheetRepo spreedSheetRepo, ModelMapper modelMapper, DepartmentRepo departmentRepo, UserRepo userRepo, EntityManager entityManager) {
        this.spreedSheetRepo = spreedSheetRepo;
        this.modelMapper = modelMapper;
        this.departmentRepo = departmentRepo;
        this.userRepo = userRepo;
        this.entityManager = entityManager;
    }

    @Override
    public SpreedSheetDTO createSpreedSheet(SpreedSheetDTO spreedSheetDTO , int departmentId , long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        Department department = this.departmentRepo.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department","Id",departmentId));
        SpreedSheet spreedSheet = this.modelMapper.map(spreedSheetDTO, SpreedSheet.class);
        spreedSheet.setDepartment(department);
        spreedSheet.setUser(user);
        SpreedSheet save = this.spreedSheetRepo.save(spreedSheet);
        return this.modelMapper.map(save,SpreedSheetDTO.class);

    }

    @Override
    public SpreedSheetDTO updateSpreedSheet(SpreedSheetDTO spreedSheetDTO, long id) {
        SpreedSheet spreedSheet = this.spreedSheetRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("SpreedSheet","Id",id));
        spreedSheet.setTitle(spreedSheetDTO.getTitle());
        spreedSheet.setDescription(spreedSheetDTO.getDescription());
        spreedSheet.setLink(spreedSheetDTO.getLink());
        spreedSheet.setImageLink(spreedSheetDTO.getImageLink());
        spreedSheet.setCategory(spreedSheetDTO.getCategory());
        SpreedSheet save = this.spreedSheetRepo.save(spreedSheet);
        return this.modelMapper.map(save,SpreedSheetDTO.class);
    }

    @Override
    public SpreedSheetDTO getSpreedSheet(long id) {
        SpreedSheet spreedSheet = this.spreedSheetRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("SpreedSheet","Id",id));
        return this.modelMapper.map(spreedSheet,SpreedSheetDTO.class);
    }

    @Transactional
    @Override
    public Void deleteSpreedSheet(long id) {
        SpreedSheet spreedSheet = this.spreedSheetRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("SpreedSheet","Id",id));
        Department department = spreedSheet.getDepartment();
        if (department != null) {
            department.getSpreedSheets().remove(spreedSheet);
        }
        this.spreedSheetRepo.delete(spreedSheet);
        return null;
    }

    @Override
    public List<SpreedSheetDTO> getByCategoryAndDepartment(String category , int departmentId) {
        Department department = this.departmentRepo.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department","Id",departmentId));
        List<SpreedSheetDTO> spreedSheetDTOS = this.spreedSheetRepo.findByCategoryAndDepartment(category,departmentId).stream().map(spreedSheet -> this.modelMapper.map(spreedSheet,SpreedSheetDTO.class)).toList();
        return spreedSheetDTOS;
    }

    @Override
    public List<SpreedSheetDTO> getByCategoryAndByUser(String category, int departmentId, long userId) {
        Department department = this.departmentRepo.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department","Id",departmentId));
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        List<SpreedSheetDTO> spreedSheetDTOS = this.spreedSheetRepo.findByCategoryAndDepartmentAndUser(category,departmentId,userId).stream().map(spreedSheet -> this.modelMapper.map(spreedSheet,SpreedSheetDTO.class)).toList();
        return spreedSheetDTOS;
    }

    @Override
    public List<SpreedSheetDTO> getByCategory(String category) {
        List<SpreedSheetDTO> spreedSheetDTOS = this.spreedSheetRepo.findByCategory(category).stream().map(spreedSheet -> this.modelMapper.map(spreedSheet,SpreedSheetDTO.class)).toList();
        return spreedSheetDTOS;
    }

    @Override
    public SpreedSheetDTO createOfficeBudget(SpreedSheetDTO spreedSheetDTO, long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        SpreedSheet spreedSheet = this.modelMapper.map(spreedSheetDTO, SpreedSheet.class);
        spreedSheet.setDepartment(null);
        spreedSheet.setUser(user);
        SpreedSheet save = this.spreedSheetRepo.save(spreedSheet);
        return this.modelMapper.map(save,SpreedSheetDTO.class);
    }
}
