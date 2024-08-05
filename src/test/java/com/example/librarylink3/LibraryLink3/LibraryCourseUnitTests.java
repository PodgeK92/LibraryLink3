package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class LibraryCourseUnitTests {

    @Autowired
    LibraryCourseService libraryCourseService;

    @Autowired
    ManageUserService manageUserService;


    @Test
    public void testAddDeleteCourse() {
        String courseName = "Course for AddCourse test";
        LibraryCourse course = new LibraryCourse();
        course.setCourseName(courseName);
        course.setTeacherName("TeacherName");
        course.setCourseSize(10);
        course.setAgeCategory("9-12");
        course.setTimeSlot("10:00 to 11:00");
        course.setDescription("CourseDescription");

        //test add course
        libraryCourseService.saveCourse(course);
        List<LibraryCourse> listOfCoursesAfterAdding = libraryCourseService.getAllCourses();
        int numberOfCoursesAfterAdding = listOfCoursesAfterAdding.size();
        LibraryCourse retrievedCourse = listOfCoursesAfterAdding.get(numberOfCoursesAfterAdding-1);
        assertTrue(retrievedCourse.getCourseName().contains(courseName));

        //test delete course
        libraryCourseService.deleteCourse(retrievedCourse.getId());
        List<LibraryCourse> listOfCoursesAfterDeleting = libraryCourseService.getAllCourses();
        int numberOfCoursesAfterDeleting = listOfCoursesAfterDeleting.size();
        assertEquals(numberOfCoursesAfterDeleting, numberOfCoursesAfterAdding-1);
    }

}
