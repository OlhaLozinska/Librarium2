package com.softserve.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @Autowired
    private SessionFactory sessionFactory;
//    @Override
//    public void addEmployee(EmployeeEntity employee) {
//        this.sessionFactory.getCurrentSession().save(employee);
//    }
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<EmployeeEntity> getAllEmployees() {
//        return this.sessionFactory.getCurrentSession().createQuery("from EmployeeEntity").list();
//    }
//    @Override
//    public void deleteEmployee(Integer employeeId) {
//        EmployeeEntity employee = (EmployeeEntity) sessionFactory.getCurrentSession().load(
//            EmployeeEntity.class, employeeId);
//        if (null != employee) {
//            this.sessionFactory.getCurrentSession().delete(employee);
//        }
//    }
}