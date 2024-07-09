package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibraryCourseController {

    @Autowired
    private LibraryCourseService libraryCourseService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/manage_courses")
    public String manageCourses(Model model) {
        model.addAttribute("courses", libraryCourseService.findAllCourses());
        return "course_list";
    }

    @GetMapping("/admin/edit_course")
    public String showEditCoursePage(@RequestParam("id") Long id, Model model) {
        LibraryCourse course = libraryCourseService.findCourseById(id);
        model.addAttribute("course", course);
        return "edit_course";
    }

    @PostMapping("/admin/update_course")
    public String updateCourse(@ModelAttribute LibraryCourse course) {
        libraryCourseService.updateCourse(course);
        return "redirect:/admin/manage_courses";
    }

    @PostMapping("/admin/delete_course")
    public String deleteCourse(@RequestParam("id") Long id) {
        libraryCourseService.deleteCourse(id);
        return "redirect:/admin/manage_courses";
    }

    @GetMapping("/admin/add_course")
    public String showAddCoursePage(Model model) {
        model.addAttribute("course", new LibraryCourse());
        return "add_course";
    }

    @PostMapping("/admin/save_course")
    public String saveCourse(@ModelAttribute LibraryCourse course) {
        libraryCourseService.saveCourse(course);
        return "redirect:/admin/manage_courses";
    }


    @GetMapping("/user/enroll_course")
    public String showEnrollCoursePage(Model model) {
        model.addAttribute("courses", libraryCourseService.findAllCourses());
        return "enroll_course";
    }

    @PostMapping("/user/enroll_course")
    public String enrollInCourse(@RequestParam("cardNumberId") String cardNumberId, @RequestParam("courseId") Long courseId, Model model) {
        User user = userRepository.findById(cardNumberId).orElse(null);
        if (user == null) {
            model.addAttribute("message", "Invalid card number ID.");
            return "enroll_course";
        }
        LibraryCourse course = libraryCourseService.findCourseById(courseId);

        try {
            libraryCourseService.enrollUserInCourse(user, course);
            model.addAttribute("message", "Successfully enrolled in course");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        model.addAttribute("courses", libraryCourseService.findAllCourses());
        return "enroll_course";
    }

    @GetMapping("/user/courses")
    public String userCourses(@RequestParam("cardNumberId") String cardNumberId, Model model) {
        User user = userRepository.findById(cardNumberId).orElse(null);
        if (user == null) {
            model.addAttribute("message", "Invalid card number ID.");
            return "user_courses";
        }
        model.addAttribute("enrollments", libraryCourseService.findEnrollmentsByUser(user));
        model.addAttribute("user", user);
        return "user_courses";
    }

    @GetMapping("/user/view_courses")
    public String viewCourses(Model model) {
        model.addAttribute("courses", libraryCourseService.findAvailableCourses());
        return "view_courses";
    }
}
