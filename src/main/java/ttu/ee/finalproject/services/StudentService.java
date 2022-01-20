package ttu.ee.finalproject.services;

import org.springframework.stereotype.Service;
import ttu.ee.finalproject.models.Student;
import ttu.ee.finalproject.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student insertStudent(Student student) {
        Optional<Student> byId = studentRepository.findById(student.getId());
        if (byId.isPresent()) {
            return null;
        }
        return studentRepository.save(student);
    }

    public Student getStudentById(String id) {
        Optional<Student> byId = studentRepository.findById(id);
        return byId.orElse(null);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
