package ttu.ee.finalproject.controllers;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ttu.ee.finalproject.models.Student;
import ttu.ee.finalproject.services.StudentService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity getStudentById(HttpServletRequest httpServletRequest, @PathVariable String id) {
        System.out.println(id);
        Student studentById = studentService.getStudentById(id);

        return ResponseEntity.ok().body(studentById);
    }
}
