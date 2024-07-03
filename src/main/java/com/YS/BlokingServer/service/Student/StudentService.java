package com.YS.BlokingServer.service.Student;

import com.YS.BlokingServer.entities.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> listOfStudents();
    //Student getStudentById(String id);
    Student getStudentByCode(String code);
    List<Student> listStudentByProgramId(String programId);
}
