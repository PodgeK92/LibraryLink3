package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class LibraryCourseUnitTests {

    @Autowired
    LibraryCourseService libraryCourseService;


    @Test
    public void testAddCourse() {
        LibraryCourse course = new LibraryCourse();
        course.setCourseName("CourseName");
        course.setTeacherName("TeacherName");
        course.setCourseSize(10);
        course.setAgeCategory("9-12");
        course.setTimeSlot("10:00 to 11:00");
        course.setDescription("CourseDescription");

        libraryCourseService.saveCourse(course);

        libraryCourseService.
    }


    @Test
    public void testEnrollUserInCourse() {

    }
}
