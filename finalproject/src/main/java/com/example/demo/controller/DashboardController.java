package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("userId", user.getId()); // 👈 Save user ID in session
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Wrong username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 👈 Clear session
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/view-details")
    public String viewDetails(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId"); // 👈 Get user ID from session
        if (userId == null) return "redirect:/login";

        List<Student> students = studentRepository.findByUserId(userId); // 👈 Only fetch own data
        model.addAttribute("students", students);
        return "view-details";
    }

    @GetMapping("/apply-details")
    public String applyDetailsForm(Model model) {
        model.addAttribute("student", new Student());
        return "apply-details";
    }

    @PostMapping("/submit-details")
    public String submitDetails(@ModelAttribute Student student, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId"); // 👈 Set user ID
        student.setUserId(userId);
        studentRepository.save(student);
        return "redirect:/view-details";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model, HttpSession session) {
        Student student = studentRepository.findById(id).orElse(null);
        Long userId = (Long) session.getAttribute("userId");

        // Prevent editing other users’ data
        if (student == null || !student.getUserId().equals(userId)) {
            return "redirect:/view-details";
        }

        model.addAttribute("student", student);
        return "edit-form";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        student.setUserId(userId); // 👈 Preserve user ownership
        studentRepository.save(student);
        return "redirect:/view-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        Student student = studentRepository.findById(id).orElse(null);
        Long userId = (Long) session.getAttribute("userId");

        // Prevent deletion if not owned by user
        if (student != null && student.getUserId().equals(userId)) {
            studentRepository.deleteById(id);
        }

        return "redirect:/view-details";
    }
}
