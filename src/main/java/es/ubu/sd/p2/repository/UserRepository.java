package es.ubu.sd.p2.repository;

import es.ubu.sd.p2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);
    User findUserByEmailuser(String email);
}
