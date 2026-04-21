package es.ubu.sd.p2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.sd.p2.model.Role;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
