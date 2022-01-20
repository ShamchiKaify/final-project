package ttu.ee.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ttu.ee.finalproject.models.PregnancyCategory;

import java.util.Optional;

@Repository
public interface PregnancyCategoryRepository extends JpaRepository<PregnancyCategory, Long> {
    Optional<PregnancyCategory> findPregnancyCategoryByPregnancyId(Long id);
}
