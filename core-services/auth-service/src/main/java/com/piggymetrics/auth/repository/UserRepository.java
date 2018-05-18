package com.piggymetrics.auth.repository;

import com.piggymetrics.auth.domain.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  Optional<User> findByUsername(String s);
}
