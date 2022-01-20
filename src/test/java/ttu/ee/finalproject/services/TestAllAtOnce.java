package ttu.ee.finalproject.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ttu.ee.finalproject.models.PregnancyCategory;
import ttu.ee.finalproject.models.Student;

import java.util.List;

@SpringBootTest
class TestAllAtOnce {
    @Autowired
    StudentService studentService;
    @Autowired
    PregnancyCategoryService pregnancyCategoryService;

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

    @Test
    public void testGetPregnancyCategoryById() {
        Long id = 1L;
        PregnancyCategory byId = pregnancyCategoryService.getPregnancyCategoryById(id);
        System.out.println(byId);
    }
}