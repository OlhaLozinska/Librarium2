package com.softserve.academy.service;

import com.softserve.academy.dao.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDao authorDao;
//    @Override
//    @Transactional
//    public void addEmployee(EmployeeEntity employee) {
//        employeeDAO.addEmployee(employee);
//    }
//    @Override
//    @Transactional
//    public List<EmployeeEntity> getAllEmployees() {
//        return employeeDAO.getAllEmployees();
//    }
//    @Override
//    @Transactional
//    public void deleteEmployee(Integer employeeId) {
//        employeeDAO.deleteEmployee(employeeId);
//    }
//    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
//        this.employeeDAO = employeeDAO;
//    }
}
