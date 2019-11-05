package crud.dao;

import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    public EntityManagerFactory entityManagerFactory;

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> userList = entityManager.createQuery("from User u").getResultList();
        return userList;
    }

    @Override
    public void addUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User getUserById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(User.class, id);
    }
}
