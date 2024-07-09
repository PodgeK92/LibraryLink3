package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryCourseService {

    @Autowired
    private LibraryCourseRepository libraryCourseRepository;

    @Autowired
    private AttendsRepository attendsRepository;

    public LibraryCourse findCourseById(Long id) {
        return libraryCourseRepository.findById(id).orElse(null);
    }

    public void updateCourse(LibraryCourse course) {
        libraryCourseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        libraryCourseRepository.deleteById(id);
    }

    public void saveCourse(LibraryCourse course) {
        libraryCourseRepository.save(course);
    }

    public List<LibraryCourse> findAllCourses() {
        return libraryCourseRepository.findAll();
    }

    public List<LibraryCourse> getAllCourses() {
        return libraryCourseRepository.findAll();
    }

    public void enrollUserInCourse(User user, LibraryCourse course) throws Exception {
        long enrolledCount = attendsRepository.countByCourseIdAndEndDateAfter(course.getId(), LocalDate.now());
        if (enrolledCount >= course.getCourseSize()) {
            throw new Exception("Course is full.");
        }

        Attends enrollment = new Attends();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setEndDate(LocalDate.now().plusWeeks(6)); // Example: 6-week enrollment period

        attendsRepository.save(enrollment);
    }

    public List<Attends> findEnrollmentsByUser(User user) {
        return attendsRepository.findByUser(user);
    }

    public List<LibraryCourse> findAvailableCourses() {
        return libraryCourseRepository.findAll().stream()
                .filter(course -> attendsRepository.countByCourseIdAndEndDateAfter(course.getId(), LocalDate.now()) < course.getCourseSize())
                .collect(Collectors.toList());
    }
}
