package ttu.ee.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ttu.ee.finalproject.models.PrescribedMedicine;

@Repository
public interface PrescribedMedicineRepository extends JpaRepository<PrescribedMedicine, Long> {

    @Query("select max(g.id) from PrescribedMedicine g")
    Integer findMaxId();
}
