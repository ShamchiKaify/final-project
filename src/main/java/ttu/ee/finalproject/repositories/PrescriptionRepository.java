package ttu.ee.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ttu.ee.finalproject.models.Prescription;
import ttu.ee.finalproject.models.UserProfile;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
    List<Prescription> findAllByPatientNid(String nid);
    List<Prescription> findAllByDoctorUserName(String uerName);

    @Query("select max(g.id) from Prescription g")
    Integer findMaxId();
}
