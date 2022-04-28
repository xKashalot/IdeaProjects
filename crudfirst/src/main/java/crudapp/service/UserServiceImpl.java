package crudapp.service;

import crudapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserServiceImpl implements UserService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User showUser(int id) {
       return (User) entityManager.createQuery("from User where id = :id").setParameter("id", id).getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> users() {
        return entityManager.createQuery("from User").getResultList();
    }



    @Transactional
    public void delete(int id) {
        entityManager.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void update(int id, User user) {
        User updatedUser = showUser(id);
        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setCity(user.getCity());
    }
}
