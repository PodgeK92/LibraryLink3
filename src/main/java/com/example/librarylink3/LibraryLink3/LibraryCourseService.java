package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryCourseService {

    @Autowired
    private LibraryCourseRepository libraryCourseRepository;

    public LibraryCourse findCourseById(Long id) {
        Optional<LibraryCourse> courseOpt = libraryCourseRepository.findById(id);
        return courseOpt.orElse(null);
    }

    public void updateCourse(LibraryCourse course) {
        libraryCourseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        libraryCourseRepository.deleteById(id);
    }

    public List<LibraryCourse> findAllCourses() {
        return libraryCourseRepository.findAll();
    }

    public List<LibraryCourse> getAllCourses() {
        return libraryCourseRepository.findAll();
    }

    public void saveCourse(LibraryCourse course) {
        libraryCourseRepository.save(course);
    }
}

