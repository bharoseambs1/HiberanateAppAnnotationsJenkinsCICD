package com.example.controller;

import com.example.model.Department;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@Controller
@RequestMapping("/department")
@Transactional
public class DepartmentController {

    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("department", new Department());
        return "department-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Department dept) {
        sessionFactory.getCurrentSession().save(dept);
        return "redirect:/employee/add";
    }
}
