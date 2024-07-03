package com.YS.BlokingServer.service.Student;

import com.YS.BlokingServer.Repository.StudentRepository;
import com.YS.BlokingServer.entities.Student;
import com.YS.BlokingServer.exceptions.StudentByCodeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> listOfStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByCode(String code) {
        return studentRepository.findByCode(code);
    }

    @Override
    public List<Student> listStudentByProgramId(String programId) {
        return studentRepository.findByProgramId(programId);
    }
}
