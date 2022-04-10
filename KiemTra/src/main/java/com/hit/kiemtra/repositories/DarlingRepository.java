package com.hit.kiemtra.repositories;

import com.hit.kiemtra.models.Darling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DarlingRepository extends JpaRepository<Darling, Long> {
    List<Darling> findAllByStatus(Integer status);
    List<Darling> findAllByStatusIsBetween(Integer statusOne, Integer statusTwo);
}
