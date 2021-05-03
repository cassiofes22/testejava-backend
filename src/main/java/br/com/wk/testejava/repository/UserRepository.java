package br.com.wk.testejava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wk.testejava.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findFirstByDsEmailIgnoreCase(String email);
}
