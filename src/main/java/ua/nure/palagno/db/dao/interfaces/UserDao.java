package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.entity.User;

import java.util.List;

/**
 * Created by Artem_Palagno on 05.09.2017.
 */
public interface UserDao {
   public void create(User user) ;
   public User get(int key) ;
   public void delete(User user);
   public List<User> getAll();
   public void changeStatus(User user);


}
