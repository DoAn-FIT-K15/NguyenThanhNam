package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
}
