package kata.pp.demo.services;

import kata.pp.demo.models.User;
import kata.pp.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService /*implements UserDetailsService*/ {

    @PersistenceContext
    private final EntityManager em;

    private final UserRepository userRepository;

    @Autowired
    public UserService(EntityManager em, UserRepository userRepository ) {
        this.em = em;
        this.userRepository = userRepository;
    }

/*    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }*/
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

//    @Modifying
//    @Query("delete from User u where u.id = :id")
//    @Transactional
//    public void delete(Long id) {
//        userRepository.deleteById(id);
//
//    }
//@Transactional
//public void delete(int id) {
//    User user = em.find(User.class, id);
//    em.getTransaction().begin();
//    em.remove(user);
//    em.getTransaction().commit();
//}
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
