package ttu.ee.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttu.ee.finalproject.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
