package com.example.librarylink3.LibraryLink3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendsRepository extends JpaRepository<Attends, Long> {
    long countByCourseIdAndEndDateAfter(Long courseId, LocalDate currentDate);
    List<Attends> findByUser(User user);
}


