package net.yorksolutions.memoapi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
