package crudapp.service;

import crudapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private EntityManager entityManager;

    @Override
    public User showUser(int id) {
       return null;
        // return usersList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void save(User user) {
//        user.setId(++USER_COUNT);
//        usersList.add(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> users() {
        Query query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }



    @Override
    public void delete(int id) {
//        usersList.removeIf(x -> x.getId() == id);
    }

    @Override
    public void update(int id, User user) {
        User updatedUser = showUser(id);
        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setCity(user.getCity());
    }
}
