package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long> {

    Account findByEmailUser(String emailUser);

}


