package hiber.service;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final SessionFactory sessionFactory;
    @Autowired
    public UserServiceImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUserByCar(String model, int series) {
        String hql = "FROM User u WHERE u.car.model = :model AND u.car.series = :series";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList();
    }


}


