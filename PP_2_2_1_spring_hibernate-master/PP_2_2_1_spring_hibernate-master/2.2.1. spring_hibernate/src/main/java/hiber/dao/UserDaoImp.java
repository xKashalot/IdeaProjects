package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User findByCar(String carModel, int carSeries) {
      TypedQuery<Car> findCarQuery = sessionFactory.getCurrentSession().createQuery("from Car where model = :carModel and series = :carSeries")
              .setParameter("carModel", carModel)
              .setParameter("carSeries", carSeries);
      Car car = findCarQuery.getSingleResult();
      System.out.println("Поиск по машине: " + car.getModel() + " " + car.getSeries() );
      User wantedUser = listUsers().stream()
              .filter(user -> user.getCar().equals(car.toString()))
              .findAny().orElse(null);
      return wantedUser;
   }

   @Override
   public User findByCar2(Car car) {
      TypedQuery<User> findUser = sessionFactory.getCurrentSession()
              .createQuery("from User where car = :car")
              .setParameter("car", car);
      User user = findUser.getSingleResult();
      System.out.println("Поиск по машине: " + user.getCar());
      return user;
   }
}
