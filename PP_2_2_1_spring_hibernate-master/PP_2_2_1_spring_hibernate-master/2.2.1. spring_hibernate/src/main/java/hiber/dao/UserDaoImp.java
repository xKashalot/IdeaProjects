package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
   public User findByCar(String car_model, int car_series) {
      TypedQuery<Car> findCarQuery = sessionFactory.getCurrentSession().createQuery("from Car where model = :car_model and series = :car_series")
              .setParameter("car_model", car_model)
              .setParameter("car_series", car_series);
      Car car = findCarQuery.getSingleResult();
      System.out.println("Поиск по машине - " + car.getModel() + " " + car.getSeries() );
      User wantedUser = listUsers().stream()
              .filter(user -> user.getCar().equals(car.toString()))
              .findAny().orElse(null);
//      for (User user : users) {
//         if (user.getCar().contains(car.toString())){
//            User wanteduser = user;
//            return wanteduser;
//         }
//      }
      return wantedUser;
   }


}
