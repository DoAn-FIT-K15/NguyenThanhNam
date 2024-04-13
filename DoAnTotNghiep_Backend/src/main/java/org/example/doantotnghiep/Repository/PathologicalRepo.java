package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.Pathological;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathologicalRepo extends JpaRepository<Pathological, Integer> {
}
