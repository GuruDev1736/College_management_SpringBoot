package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Attendance;
import com.taskease.college.Model.Department;
import com.taskease.college.Model.Faculty.SpreedSheet;
import com.taskease.college.Model.User;
import com.taskease.college.PayLoad.AttendanceDTO;
import com.taskease.college.PayLoad.SpreedSheetDTO;
import com.taskease.college.Repository.AttendanceRepo;
import com.taskease.college.Repository.DepartmentRepo;
import com.taskease.college.Repository.UserRepo;
import com.taskease.college.Service.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;


@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final ModelMapper modelMapper;

    private final DepartmentRepo departmentRepo;
    private final AttendanceRepo attendanceRepo;
    private final UserRepo userRepo;

    public AttendanceServiceImpl(ModelMapper modelMapper, DepartmentRepo departmentRepo, AttendanceRepo attendanceRepo, UserRepo userRepo) {
        this.modelMapper = modelMapper;
        this.departmentRepo = departmentRepo;
        this.attendanceRepo = attendanceRepo;
        this.userRepo = userRepo;
    }

    @Override
    public AttendanceDTO createAttendanceSheet(AttendanceDTO attendanceDTO, int departmentId , long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        Department department = this.departmentRepo.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department","Id",departmentId));
        Attendance attendance = this.modelMapper.map(attendanceDTO, Attendance.class);
        attendance.setDepartment(department);
        attendance.setUser(user);
        Attendance save = this.attendanceRepo.save(attendance);
        return this.modelMapper.map(save, AttendanceDTO.class);

    }

    @Override
    public AttendanceDTO updateAttendanceSheet(AttendanceDTO attendanceDTO, long id) {
        Attendance attendance = this.attendanceRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Attendance Sheet ","Id",id));
        attendance.setTitle(attendanceDTO.getTitle());
        attendance.setDescription(attendanceDTO.getDescription());
        attendance.setLink(attendanceDTO.getLink());
        attendance.setCategory(attendanceDTO.getCategory());
        Attendance save = this.attendanceRepo.save(attendance);
        return this.modelMapper.map(save,AttendanceDTO.class);
    }

    @Override
    public AttendanceDTO getAttendanceSheet(long id) {
        Attendance attendance = this.attendanceRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Attendance Sheet","Id",id));
        return this.modelMapper.map(attendance,AttendanceDTO.class);
    }

    @Override
    public Void deleteAttendanceSheet(long id) {
        Attendance attendance = this.attendanceRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Attendance Sheet","Id",id));
        Department department = attendance.getDepartment();
        if (department != null) {
            department.getSpreedSheets().remove(attendance);
        }
        this.attendanceRepo.delete(attendance);
        return null;
    }

    @Override
    public List<AttendanceDTO> getByCategory(String category, int departmentId) {
        Department department = this.departmentRepo.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department","Id",departmentId));
        List<AttendanceDTO> attendanceDTOS = this.attendanceRepo.findByCategoryAndDepartment(category,departmentId).stream().map(attendance -> this.modelMapper.map(attendance,AttendanceDTO.class)).toList();
        return attendanceDTOS;
    }
}
