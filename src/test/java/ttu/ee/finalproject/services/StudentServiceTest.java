package ttu.ee.finalproject.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ttu.ee.finalproject.models.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    StudentService studentService;

    @Test
    public void testInsertStudent() {
        Student student = new Student();
        student.setId("1");
        student.setName("Baburao Apte");

        Student insertStudent = studentService.insertStudent(student);
        System.out.println(insertStudent);
    }

    @Test
    public void getAllStudent() {
        List<Student> allStudent = studentService.getAllStudent();

        allStudent.forEach(System.out::println);
    }
}