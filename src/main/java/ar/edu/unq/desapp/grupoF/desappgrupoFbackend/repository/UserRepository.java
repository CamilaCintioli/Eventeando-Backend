package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
}
