package com.YS.BlokingServer.controller;

import com.YS.BlokingServer.entities.Student;
import com.YS.BlokingServer.service.Student.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = "*")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/addStudent")
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }
    @GetMapping("/allStudents")
    public List<Student> findAllStudents(){
        return studentService.listOfStudents();
    }
    @GetMapping("/byCode/{code}")
    public Student findStudentByCode(@PathVariable String code){
        return studentService.getStudentByCode(code);
    }@GetMapping("/byProgramId/{programId}")
    public List<Student> findStudentsByProgramId(@PathVariable String programId){
        return studentService.listStudentByProgramId(programId);
    }
}
