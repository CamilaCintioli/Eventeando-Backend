package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
    List<User> findAll();
}
