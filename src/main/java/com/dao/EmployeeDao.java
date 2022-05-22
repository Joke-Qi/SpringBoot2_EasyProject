package com.dao;

import com.cn.Department;
import com.cn.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//员工Dao
@Repository //让Spring进行托管
public class EmployeeDao {

    //模拟数据库中的数据
    @Autowired
    private DepartmentDao departmentDao;

    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;

    static {
        employees = new HashMap<Integer, Employee>();   //创建一个员工表
        employees.put(1001, new Employee(1001, "AA", "1479089575@qq.com", 0, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "BB", "1479189575@qq.com", 1, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "CC", "1479289575@qq.com", 0, new Department(103, "教研部")));
        employees.put(1004, new Employee(1004, "DD", "1479389575@qq.com", 1, new Department(104, "后勤部")));
    }

    private static Integer eid = 1005;

    //增加一个员工
    public void addEmployee(Employee employee){
        if (employee.getId()<1000){
            employee.setId(eid++);
        }
        employees.put(employee.getId(),employee);
    }

    //删除一个员工
    public void deleteEmployee(int id){
        employees.remove(id);
    }

    //通过id查询员工
    public Employee findById(int id){
        return employees.get(id);
    }

    //查询所有员工信息
    public Collection<Employee> findAllEmployee(){
        return employees.values();
    }

}

