package it.epicode.gestioneEventi.repository;

import it.epicode.gestioneEventi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String email);

    List<User> findByIdIn(List<Integer> ids);
}
