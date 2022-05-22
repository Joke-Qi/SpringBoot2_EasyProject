package com.controller;

import com.cn.Department;
import com.cn.Employee;
import com.dao.DepartmentDao;
import com.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentdao;

    //跳转到员工列表页面
    @RequestMapping("/toEmpls")
    public String toEmpls(Model model){
        Collection<Employee> emp = employeeDao.findAllEmployee();
        model.addAttribute("emps",emp);
        return "emp/list";
    }

    //跳转到添加员工页面
    @RequestMapping("/toAddEmp")
    public String toAddEmp(Model model){
        Collection<Department> departments = departmentdao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/addEmp";
    }

    //添加员工操作
    @RequestMapping("/addEmps")
    public String addEmpls(Employee employee,int departmentId){
        employee.setDepartment(departmentdao.getDepartmentById(departmentId));
        employeeDao.addEmployee(employee);
        return "redirect:/toEmpls";
    }

    //跳转到主页面
    @RequestMapping("/dash")
    public String dash(){
        return "dashboard";
    }

    //跳转到修改员工的页面
    @RequestMapping("/toUpdateEmp/{id}")
    public String toUpdateEmp(Model model,@PathVariable("id")Integer id){
        Collection<Department> departments = departmentdao.getDepartments();
        model.addAttribute("departments",departments);
        Employee emp = employeeDao.findById(id);
        model.addAttribute("emp",emp);
        return "emp/updateEmp";
    }

    //修改员工
    @RequestMapping("/updateEmps")
    public String updateEmpls(Employee employee,Integer id ,int departmentId){
        employeeDao.deleteEmployee(id);
        employee.setId(id);
        employee.setDepartment(departmentdao.getDepartmentById(departmentId));
        employeeDao.addEmployee(employee);
        return "redirect:/toEmpls";
    }

    //删除员工
    @RequestMapping("/deleteEmps/{id}")
    public String deleteEmpls(@PathVariable("id")Integer id){
        employeeDao.deleteEmployee(id);
        return "redirect:/toEmpls";
    }

}
