package ttu.ee.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttu.ee.finalproject.models.Generic;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenericRepository extends JpaRepository<Generic, Long> {
    Optional<Generic> findGenericByGenericId(Long id);
    Optional<Generic> findGenericByGenericNameContaining(String search);
    List<Generic> findAllByGenericId(String id);
}
