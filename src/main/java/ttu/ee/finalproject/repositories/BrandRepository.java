package ttu.ee.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttu.ee.finalproject.models.Brand;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findBrandByBrandId(Long id);
    Optional<Brand> findFirstByBrandNameContaining(String name);
    List<Brand> findAllByGenericId(Long id);
}
