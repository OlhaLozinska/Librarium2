package com.softserve.academy.dao;

import com.softserve.academy.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public User getUserById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public int getUserStatisticAverageAge() {
        String hql = "select avg(year(current_date)-year(birthdayDate)) FROM User WHERE birthdayDate is not null";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return ((Double) results.get(0)).intValue();
    }

    @Override
    public int getUserAverageTimeOfUsingLibrary() {
        String hql = "select avg(((year(current_date)*365)+(month(current_date)*12)+day(current_date))" +
            "-((year(createdAt)*365)+(month(createdAt)*12)+day(createdAt))) FROM User";
        List results = this.sessionFactory.getCurrentSession().createQuery(hql).list();
        return ((Double) results.get(0)).intValue();
    }

    @Override
    public int getUserAverageAgeByBookId(int bookId) {
        String hql = "select (avg(((year(current_date)*365)+(month(current_date)*12)+day(current_date))" +
            "-((year(U.birthdayDate)*365)+(month(U.birthdayDate)*12)+day(U.birthdayDate))))/365 " +
            "from  Order As O left join User AS U On U.id = O.reader.id and O.book.id=:bookId";

        List results = this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("bookId", bookId).list();

        return ((Double) results.get(0)).intValue();
    }

}
