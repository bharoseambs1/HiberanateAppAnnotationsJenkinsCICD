package com.example.controller;

import com.example.model.Employee;
import com.example.model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Controller
@RequestMapping("/employee")
@Transactional
public class EmployeeController {

    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("employees",
                sessionFactory.getCurrentSession()
                        .createQuery(
                                "select e from Employee e left join fetch e.department",
                                Employee.class
                        ).list());
        return "employee-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments",
                sessionFactory.getCurrentSession()
                        .createQuery("from Department", Department.class).list());
        return "employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee) {

        Department dept = sessionFactory.getCurrentSession()
                .get(Department.class, employee.getDepartment().getDeptId());

        employee.setDepartment(dept);
        sessionFactory.getCurrentSession().saveOrUpdate(employee);

        return "redirect:/employee/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Employee emp = sessionFactory.getCurrentSession()
                .createQuery(
                        "select e from Employee e left join fetch e.department where e.empId=:id",
                        Employee.class)
                .setParameter("id", id)
                .uniqueResult();

        model.addAttribute("employee", emp);
        model.addAttribute("departments",
                sessionFactory.getCurrentSession()
                        .createQuery("from Department", Department.class).list());

        return "employee-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        sessionFactory.getCurrentSession().delete(
                sessionFactory.getCurrentSession().get(Employee.class, id));
        return "redirect:/employee/list";
    }
}
